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

import java.util.Calendar;


public class AdminDashboard extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Intent intent;
    ImageButton LogOutButtonBackground;
    ImageButton imageButton4;
    ImageButton calendarbutton;


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
                intent = new Intent(AdminDashboard.this,LogInPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        calendarbutton = findViewById(R.id.calendarbutton);

        calendarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            }
        }, year, month, date);
        datePickerDialog.show();
    }
}

