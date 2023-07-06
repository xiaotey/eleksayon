package com.example.eleksayon;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class CandidateInfoCard extends AppCompatActivity {

    private Button voteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_infocard);

        voteButton = findViewById(R.id.vote_button_1);
    }
}
