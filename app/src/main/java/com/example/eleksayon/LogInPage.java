package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.eleksayon.databinding.ActivityLogInPageBinding;

public class LogInPage extends AppCompatActivity {

    ActivityLogInPageBinding binding;
    DBHandler databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DBHandler(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if (email.equals("")|| password.equals(""))
                    Toast.makeText(LogInPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);
                    Boolean checkAdminRights = databaseHelper.checkEmailPasswordAdmin(email, password);

                    if (checkCredentials == true) {
                        Toast.makeText(LogInPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), student_dashboard.class);
                        startActivity(intent);
                    } else if (checkAdminRights == true) {
                            Toast.makeText(LogInPage.this, "Admin account logged in", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), AdminDashboard.class);
                            startActivity(intent1);
                    } else if (checkCredentials == false || checkAdminRights == false){
                        Toast.makeText(LogInPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });

    }
}