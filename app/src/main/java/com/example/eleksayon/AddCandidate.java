package com.example.eleksayon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.graphics.BitmapFactory;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Spinner;
import android.net.Uri;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import com.example.eleksayon.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCandidate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView uploadedImage;
    private static final int SELECT_IMAGE_REQUEST = 1;

    private Button btnUpload;
    private ImageView imagePreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        Spinner spinner2 = findViewById(R.id.spinner2);

        // Create a list of items for the dropdown
        List<String> items = new ArrayList<>();
        items.add("Governor");
        items.add("Vice Governor");
        items.add("TadStraw Committee");
        items.add("Food Committee");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);


        Spinner spinner3 = findViewById(R.id.spinner3);

        List<String> course = new ArrayList<>();

        course.add("BS Computer Engineering");
        course.add("BS Electrical Engineering");
        course.add("BS Electronics & Communications Engineering");
        course.add("BS Civil Engineering");
        course.add("BS Ceramics Engineering");
        course.add("BS Chemical Engineering");
        course.add("BS Mining Engineering");
        course.add("BS Environmental Engineering");
        course.add("BS Mechanical Engineering");
        course.add("BS Metallurgical Engineering");
        course.add("BS Industrial Automation and Mechatronics");

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, course);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setOnItemSelectedListener(this);
        spinner3.setAdapter(courseAdapter);



        Spinner spinner4 = findViewById(R.id.spinner4);

        // Create a list of items for the dropdown
        List<String> year = new ArrayList<>();
        year.add("1st Year");
        year.add("2nd Year");
        year.add("3rd Year");
        year.add("4th Year");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
}
