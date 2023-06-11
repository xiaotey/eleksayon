package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.eleksayon.databinding.ActivityRegisterPageBinding;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityRegisterPageBinding binding;
    DBHandler databaseHelper;

        Button signupButton;
        private EditText editTextTextPassword;
        private EditText editTextTextPassword2;

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
                String password = binding.editTextTextPassword.getText().toString();
                String confirmPassword = binding.editTextTextPassword2.getText().toString();

                if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(RegisterPage.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if (password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if (checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email, password);

                            if (insert == true){
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
        items.add("Chemical Engineering Technology");


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
        // Do nothing
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void checkPasswordsMatch() {
        String password = editTextTextPassword.getText().toString();
        String confirmPassword = editTextTextPassword2.getText().toString();

        if (password.equals(confirmPassword)) {
            editTextTextPassword2.setError(null);
        } else {
            editTextTextPassword2.setError("Passwords do not match");
        }
    }
}
