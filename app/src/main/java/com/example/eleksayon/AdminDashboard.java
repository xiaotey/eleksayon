package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;


public class AdminDashboard extends AppCompatActivity {

    private ImageButton addButton;
    private ImageButton calendarButton;
    private ImageButton logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        addButton = findViewById(R.id.imageButton4);
       addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AddCandidate.class);
                startActivity(intent);
            }
        });
        logoutButton = findViewById(R.id.LogOutButtonBackground);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashboard.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calendarButton = findViewById(R.id.calendarbutton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog();

            }
        });
        }
        private void openDialog(){

            DatePickerDialog dialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


                }
            }, 2023, 0, 15);
            dialog.show();
        }

    }

