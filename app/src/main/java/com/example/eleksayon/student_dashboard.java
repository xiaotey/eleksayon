package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.eleksayon.databinding.ActivityStudentVoteBinding;

import java.util.Calendar;

public class student_dashboard extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Intent intent;
    private Button button7;
    private Button button5;
    public Button vbutton;
    public Button viewbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        button7 = findViewById(R.id.button7);
        sharedPreferences = getSharedPreferences("AdminDashboard", MODE_PRIVATE);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(student_dashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
                intent = new Intent(student_dashboard.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        vbutton = findViewById(R.id.buttonvote);
        viewbutton = findViewById(R.id.button3);
        vbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_dashboard.this, StudentVoteActivity.class);
                startActivity(intent);
            }
        });
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_dashboard.this, ViewPage.class);
                startActivity(intent);
            }
        });
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
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




