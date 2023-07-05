package com.example.eleksayon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Environment;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MainDatabase.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_VOTERS = "voters";
    private static final String TABLE_ADMINS = "admins";
    private static final String TABLE_CANDIDATES = "candidates";

    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_HAS_VOTED = "has_voted";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_YEAR_LEVEL = "year_level";
    private static final String COLUMN_COURSE_CANDIDATE = "course_candidate";
    private static final String COLUMN_POSITION = "position";
    private static final String COLUMN_PLATFORM = "platform";
    private static final String COLUMN_IMAGE_PATH = "image_path";

    private Context context;

    public DBHandler(@SuppressLint("StaticFieldLeak") Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createVotersTable = "CREATE TABLE " + TABLE_VOTERS + " (email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT, has_voted INTEGER DEFAULT 0)";
        String createAdminUsersTable = "CREATE TABLE " + TABLE_ADMINS + " (email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT)";
        String createCandidatesTable = "CREATE TABLE " + TABLE_CANDIDATES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, year_level TEXT, course_candidate TEXT, image_path TEXT, first_name TEXT, position TEXT, platform TEXT, last_name TEXT)";

        db.execSQL(createVotersTable);
        db.execSQL(createAdminUsersTable);
        db.execSQL(createCandidatesTable);

        ContentValues adminValues = new ContentValues();
        adminValues.put(COLUMN_EMAIL, "jawhara.amirol@g.msuiit.edu.ph");
        adminValues.put(COLUMN_PASSWORD, "12345");
        adminValues.put(COLUMN_ID, "2021-0000");
        db.insert(TABLE_ADMINS, null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Add the new column
            String alterTableQuery = "ALTER TABLE " + TABLE_CANDIDATES + " ADD COLUMN " + COLUMN_COURSE_CANDIDATE + " TEXT";
            db.execSQL(alterTableQuery);
        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIDATES);
        onCreate(db);
    }

    public boolean insertData(String email, String password, String id, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_COURSE, course);
        long result = db.insert(TABLE_VOTERS, null, contentValues);
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VOTERS, new String[]{COLUMN_EMAIL}, COLUMN_EMAIL + " = ?", new String[]{email}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VOTERS, new String[]{COLUMN_EMAIL}, COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailAdmin(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMINS, new String[]{COLUMN_EMAIL}, COLUMN_EMAIL + " = ?", new String[]{email}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailPasswordAdmin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMINS, new String[]{COLUMN_EMAIL}, COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private String saveImageToStorage(Bitmap imageBitmap, String fileName) {
        FileOutputStream fos = null;
        String imagePath = null;
        try {
            File directory = context.getDir("images", Context.MODE_PRIVATE);
            File file = new File(directory, fileName);
            fos = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            imagePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagePath;
    }

    public boolean insertCandidate(String firstName, String lastName, String yearLevel, String course, String position, String platform, Bitmap imageBitmap) {
        String fileName = generateUniqueFileName();
        String imagePath = saveImageToStorage(imageBitmap, fileName);

        if (imagePath != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_FIRST_NAME, firstName);
            contentValues.put(COLUMN_LAST_NAME, lastName);
            contentValues.put(COLUMN_YEAR_LEVEL, yearLevel);
            contentValues.put(COLUMN_COURSE_CANDIDATE, course);
            contentValues.put(COLUMN_POSITION, position);
            contentValues.put(COLUMN_PLATFORM, platform);
            contentValues.put(COLUMN_IMAGE_PATH, imagePath);
            long result = db.insert(TABLE_CANDIDATES, null, contentValues);
            return result != -1;
        } else {
            return false;
        }
    }

        private String generateUniqueFileName() {
        long timestamp = System.currentTimeMillis();
        return "image_" + timestamp + ".jpg";
    }
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidateList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CANDIDATES, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int firstNameIndex = cursor.getColumnIndex(COLUMN_FIRST_NAME);
            int lastNameIndex = cursor.getColumnIndex(COLUMN_LAST_NAME);
            int yearLevelIndex = cursor.getColumnIndex(COLUMN_YEAR_LEVEL);
            int courseCandidateIndex = cursor.getColumnIndex(COLUMN_COURSE);
            int positionIndex = cursor.getColumnIndex(COLUMN_POSITION);
            int imagePathIndex = cursor.getColumnIndex(COLUMN_IMAGE_PATH);
            int platformIndex = cursor.getColumnIndex(COLUMN_PLATFORM);

            do {
                int id = cursor.getInt(idIndex);
                String firstName = cursor.getString(firstNameIndex);
                String lastName = cursor.getString(lastNameIndex);
                String yearLevel = cursor.getString(yearLevelIndex);
                String courseCandidate = courseCandidateIndex != -1 ? cursor.getString(courseCandidateIndex) : "";
                String position = positionIndex != -1 ? cursor.getString(positionIndex) : "";
                String platform = platformIndex != -1 ? cursor.getString(platformIndex) : "";
                String imagePath = imagePathIndex != -1 ? cursor.getString(imagePathIndex) : "";

                Candidate candidate = new Candidate(firstName, lastName, yearLevel, courseCandidate, position, platform, imagePath);
                candidateList.add(candidate);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return candidateList;
    }
}