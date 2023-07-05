package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.eleksayon.Reminder;

import java.util.Calendar;


public class AdminDashboard extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Intent intent;
    ImageButton LogOutButtonBackground;
    ImageButton imageButton4;
    ImageButton calendarbutton;
    ImageButton imageButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        imageButton4 = findViewById(R.id.imageButton4);

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminDashboard.this,AddCandidate.class);
                startActivity(intent);
            }
        });

        LogOutButtonBackground = findViewById(R.id.LogOutButtonBackground);
        sharedPreferences=getSharedPreferences("AdminDashboard", MODE_PRIVATE);

        LogOutButtonBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(AdminDashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
                intent = new Intent(AdminDashboard.this,LogInPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        calendarbutton = findViewById(R.id.calendarbutton);
        calendarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, Reminder.class);
                startActivity(intent);
            }
        });
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminDashboard.this,ViewPage.class);
                startActivity(intent);
            }
        });
    }
}

