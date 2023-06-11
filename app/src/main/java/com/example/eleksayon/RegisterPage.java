package com.example.eleksayon;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        Button button5;
        private EditText editTextTextPassword;
        private EditText editTextTextPassword2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showToast("Account Created");
            }
        });



        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextTextPassword.getText().toString();
                String confirmPassword = editTextTextPassword2.getText().toString();

                if (password.equals(confirmPassword)) {
                    showToast("Account Created");
                } else {
                    showToast("Password do not match");
                }
            }
        });

        editTextTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPasswordsMatch();
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
        items.add("BS Civil Engineering");
        items.add("BS Environmental Engineering");
        items.add("BS Mechanical Engineering");
        items.add("BS Metallurgical Engineering");
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
