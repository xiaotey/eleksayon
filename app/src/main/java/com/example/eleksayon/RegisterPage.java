package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import com.example.eleksayon.databinding.ActivityRegisterPageBinding;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityRegisterPageBinding binding;
    DBHandler databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DBHandler(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.EmailAddress.getText().toString();
                String id = binding.idNumber.getText().toString();
                String course = binding.spinner.getSelectedItem().toString();
                String password = binding.editTextTextPassword.getText().toString();
                String confirmPassword = binding.editTextTextPassword2.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!email.endsWith("@g.msuiit.edu.ph")) {
                    Toast.makeText(RegisterPage.this, "Please use My.IIT email only", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmPassword)){
                        boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if (!checkUserEmail){
                            boolean insert = databaseHelper.insertData(email, password, id, course);

                            if (insert){
                                Toast.makeText(RegisterPage.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterPage.this, "Signup failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterPage.this, "User already exists, please login", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> items = new ArrayList<>();
        items.add("BS Computer Engineering");
        items.add("BS Electrical Engineering");
        items.add("BS Electronics & Communications Engineering");
        items.add("BS Civil Engineering");
        items.add("BS Ceramics Engineering");
        items.add("BS Chemical Engineering");
        items.add("BS Mining Engineering");
        items.add("BS Environmental Engineering");
        items.add("BS Mechanical Engineering");
        items.add("BS Metallurgical Engineering");
        items.add("BS Industrial Automation and Mechatronics");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();

        Toast.makeText(this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
