package com.example.eleksayon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CandidateInfoCard extends AppCompatActivity {

    private Button voteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_infocard);

        voteButton = findViewById(R.id.vote_button_1);
    }

    public void onVoteButtonClicked(View view) {
        voteButton.setVisibility(View.GONE);
        Toast.makeText(this, "You have voted for this candidate.", Toast.LENGTH_SHORT).show();
    }
}
