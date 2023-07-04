package com.example.eleksayon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCandidate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView uploadedImage;
    private static final int SELECT_IMAGE_REQUEST = 1;

    private Button btnUpload;
    private ImageView imagePreview;

    private DBHandler dbHandler; // Assuming you have an instance of DBHandler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        // Initialize DBHandler instance
        dbHandler = new DBHandler(this);

        Spinner spinner2 = findViewById(R.id.spinner2);
        List<String> positions = new ArrayList<>();
        positions.add("Governor");
        positions.add("Vice Governor");
        positions.add("TadStraw Committee");
        positions.add("Food Committee");
        positions.add("Ethics & Grievances Committee");
        positions.add("Tabulations Committee");
        positions.add("Literary Committee");
        positions.add("Sports and Development Affairs Committee");
        positions.add("Culture & Arts Committee");
        positions.add("Publication Committee");
        positions.add("Environment & Community Extension Committee");
        positions.add("TADSTRAW Committee");
        positions.add("Documentation Committee");
        positions.add("Facilities Committee");
        positions.add("Budget & Finance Committee");
        positions.add("Ways & Means Committee");
        positions.add("Executive Committee");
        positions.add("Special Events Committee");
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(positionAdapter);
        spinner2.setOnItemSelectedListener(this);

        Spinner spinner3 = findViewById(R.id.spinner3);
        List<String> courses = new ArrayList<>();
        courses.add("BS Computer Engineering");
        courses.add("BS Electrical Engineering");
        courses.add("BS Electronics & Communications Engineering");
        courses.add("BS Civil Engineering");
        courses.add("BS Ceramics Engineering");
        courses.add("BS Chemical Engineering");
        courses.add("BS Mining Engineering");
        courses.add("BS Environmental Engineering");
        courses.add("BS Mechanical Engineering");
        courses.add("BS Metallurgical Engineering");
        courses.add("BS Industrial Automation and Mechatronics");
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setOnItemSelectedListener(this);
        spinner3.setAdapter(courseAdapter);

        Spinner spinner4 = findViewById(R.id.spinner4);
        List<String> years = new ArrayList<>();
        years.add("1st Year");
        years.add("2nd Year");
        years.add("3rd Year");
        years.add("4th Year");
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(yearAdapter);
        spinner4.setOnItemSelectedListener(this);

        btnUpload = findViewById(R.id.btnUpload);
        imagePreview = findViewById(R.id.imagePreview);
        uploadedImage = findViewById(R.id.uploadedImage);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Find the "Create Candidate" button and set an onClickListener
        Button btnCreateCandidate = findViewById(R.id.button6);
        btnCreateCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the candidate details from the form
                EditText etFirstName = findViewById(R.id.editTextTextPersonName3);
                EditText etLastName = findViewById(R.id.editTextTextPersonName);
                Spinner spinnerYear = findViewById(R.id.spinner4);
                Spinner spinnerCourse = findViewById(R.id.spinner3);
                Spinner spinnerPosition = findViewById(R.id.spinner2);
                EditText etPlatform = findViewById(R.id.editTextTextPersonName8);
                Bitmap imageBitmap = ((BitmapDrawable) uploadedImage.getDrawable()).getBitmap();

                // Extract the selected values from spinners
                String yearLevel = spinnerYear.getSelectedItem().toString();
                String course = spinnerCourse.getSelectedItem().toString();
                String position = spinnerPosition.getSelectedItem().toString();

                // Get the first name, last name, and platform from the EditText fields
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String platform = etPlatform.getText().toString().trim();

                // Insert the candidate into the database
                insertCandidate(firstName, lastName, yearLevel, course, position, platform, imageBitmap);
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            if (imageUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imagePreview.setImageBitmap(bitmap);
                    uploadedImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected item from the spinner
        String selectedItem = parent.getItemAtPosition(position).toString();

        // Display a toast message with the selected item
        Toast.makeText(this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void insertCandidate(String firstName, String lastName, String yearLevel, String course, String position, String platform, Bitmap imageBitmap) {
        boolean isInserted = dbHandler.insertCandidate(firstName, lastName, yearLevel, course, position, platform, imageBitmap);

        if (isInserted) {
            Toast.makeText(this, "Candidate inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to insert candidate", Toast.LENGTH_SHORT).show();
        }
    }
}