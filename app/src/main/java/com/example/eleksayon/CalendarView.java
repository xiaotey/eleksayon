package com.example.eleksayon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarView extends AppCompatActivity {

    android.widget.CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        calendarView = findViewById(R.id.Calendar);
        calendar = Calendar.getInstance();

        setDate(1, 1, 2023);
        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(CalendarView.this, day + "/" + month + "/" + year , Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month );
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli= calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}