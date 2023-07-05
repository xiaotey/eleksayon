package com.example.eleksayon;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

        Comparator<Candidate> positionComparator = new Comparator<Candidate>() {
            List<String> positionOrder = Arrays.asList(
                    "Governor",
                    "Vice Governor",
                    "Food Committee",
                    "Ethics & Grievances Committee",
                    "Tabulations Committee",
                    "Literary Committee",
                    "Sports and Development Affairs Committee",
                    "Culture & Arts Committee",
                    "Publication Committee",
                    "Environment & Community Extension Committee",
                    "TADSTRAW Committee",
                    "Documentation Committee",
                    "Facilities Committee",
                    "Budget & Finance Committee",
                    "Ways & Means Committee",
                    "Executive Committee",
                    "Special Events Committee"
            );

            @Override
            public int compare(Candidate candidate1, Candidate candidate2) {
                String position1 = candidate1.getPosition();
                String position2 = candidate2.getPosition();

                // Get the indices of the positions in the defined order
                int index1 = positionOrder.indexOf(position1);
                int index2 = positionOrder.indexOf(position2);

                // Compare the indices to determine the sorting order
                if (index1 == index2) {
                    return 0;
                } else if (index1 == -1) {
                    return 1; // candidate1 is a committee (not in the predefined order), move it down
                } else if (index2 == -1) {
                    return -1; // candidate2 is a committee (not in the predefined order), move it down
                } else {
                    return Integer.compare(index1, index2);
                }
            }
        };

        // Sort the candidateList based on the defined positionComparator
        Collections.sort(candidateList, positionComparator);

        // Initialize the CandidateAdapter with the candidateList
        candidateAdapter = new CandidateAdapter(this, candidateList);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(candidateAdapter);

    }
}


