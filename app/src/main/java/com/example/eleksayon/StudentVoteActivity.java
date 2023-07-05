package com.example.eleksayon;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_vote);

        // Initialize the DBHandler
        dbHandler = new DBHandler(this);

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
}


