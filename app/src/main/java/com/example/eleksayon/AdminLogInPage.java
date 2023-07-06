package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.eleksayon.databinding.ActivityLogInPageAdminBinding;

public class AdminLogInPage extends AppCompatActivity {
    DBHandler databaseHelper;
    ActivityLogInPageAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInPageAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DBHandler(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if (email.equals("")|| password.equals(""))
                    Toast.makeText(AdminLogInPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);
                    boolean checkAdminRights = databaseHelper.checkEmailPasswordAdmin(email, password);

                    if (checkCredentials) {
                        Toast.makeText(AdminLogInPage.this, "Account has no admin rights, login as user instead", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                        startActivity(intent);
                    } else if (checkAdminRights) {
                        Toast.makeText(AdminLogInPage.this, "Admin account logged in", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), AdminDashboard.class);
                        startActivity(intent1);
                    } else if (!checkCredentials || checkAdminRights == false){
                        Toast.makeText(AdminLogInPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.studentsigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogInPage.this, LogInPage.class);
                startActivity(intent);
            }
        });

    }
}

