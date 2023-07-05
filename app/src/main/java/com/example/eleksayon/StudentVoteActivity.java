package com.example.eleksayon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentVoteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CandidateAdapter candidateAdapter;
    private List<Candidate> candidateList;
    private DBHandler dbHandler;

    private Button voteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_vote);

        // Initialize the DBHandler
        dbHandler = new DBHandler(this);

        voteButton = findViewById(R.id.vote_button_1);
        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve candidate data from the database
        candidateList = dbHandler.getAllCandidates();

        // Initialize the CandidateAdapter with the candidateList
        candidateAdapter = new CandidateAdapter(this, candidateList);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(candidateAdapter);
    }
    public void onVoteButtonClicked(View view) {
        voteButton.setVisibility(View.GONE);
        Toast.makeText(this, "You have voted for this candidate.", Toast.LENGTH_SHORT).show();
    }
}


