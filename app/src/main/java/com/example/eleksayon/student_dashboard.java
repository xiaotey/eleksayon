package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eleksayon.databinding.ActivityStudentVoteBinding;

public class student_dashboard extends AppCompatActivity {
    public Button vbutton;
    public Button viewbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

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

    }
}