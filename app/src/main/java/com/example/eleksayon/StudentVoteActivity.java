package com.example.eleksayon;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StudentVoteActivity extends AppCompatActivity {

    private Button voteButton1;
    private Button voteButton2;
    private boolean isPressMeVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_vote);

        voteButton1 = findViewById(R.id.vote_button_1);
        voteButton2 = findViewById(R.id.vote_button_2);
    }

    public void onvoteButton1Clicked(View view) {
        isPressMeVisible = false;
        updateButtonVisibility();
    }

    public void onvoteButton2Clicked(View view) {
        isPressMeVisible = true;
        updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        if (isPressMeVisible) {
            voteButton1.setVisibility(View.VISIBLE);
            voteButton2.setVisibility(View.GONE);
            Toast.makeText(this, "You have voted for this Candidate.", Toast.LENGTH_SHORT).show();
        } else {
            voteButton1.setVisibility(View.GONE);
            voteButton2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "You have voted for this Candidate.", Toast.LENGTH_SHORT).show();
        }
    }
}

