package com.example.eleksayon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MainDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VOTERS = "voters";
    private static final String TABLE_ADMINS = "admins";
    private static final String TABLE_CANDIDATES = "candidates";

    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_HAS_VOTED = "has_voted";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createVotersTable = "CREATE TABLE " + TABLE_VOTERS + " (email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT, has_voted INTEGER DEFAULT 0)";
        String createAdminUsersTable = "CREATE TABLE " + TABLE_ADMINS + " (email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT)";
        String createParticipantsTable = "CREATE TABLE " + TABLE_CANDIDATES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, year_level TEXT, course TEXT, position TEXT, platform TEXT)";

        db.execSQL(createVotersTable);
        db.execSQL(createAdminUsersTable);
        db.execSQL(createParticipantsTable);

        ContentValues adminValues = new ContentValues();
        adminValues.put("email", "jawhara.amirol@g.msuiit.edu.ph");
        adminValues.put("password", "12345");
        adminValues.put("id", "2021-0000");
        db.insert(TABLE_ADMINS, null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VOTERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VOTERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailAdmin(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailPasswordAdmin(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertParticipant(String firstName, String lastName, String yearLevel, String course, String position, String platform) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("year_level", yearLevel);
        contentValues.put("course", course);
        contentValues.put("position", position);
        contentValues.put("platform", platform);
        long result = db.insert(TABLE_CANDIDATES, null, contentValues);
        return result != -1;
    }
}