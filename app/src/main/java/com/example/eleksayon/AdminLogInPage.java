package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLogInPage extends AppCompatActivity {

    Button studentsigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page_admin);

        studentsigninButton = findViewById(R.id.studentsigninButton);

        studentsigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogInPage.this, LogInPage.class);
                startActivity(intent);
            }
        });
    }
}