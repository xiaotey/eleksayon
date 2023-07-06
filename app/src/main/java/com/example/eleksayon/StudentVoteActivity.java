package com.example.eleksayon;

import android.os.Bundle;
import android.widget.Button;
import android.content.SharedPreferences;
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

    private Button voteButton;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_vote);
        sharedPreferences = getSharedPreferences("AdminDashboard", MODE_PRIVATE);
        dbHandler = new DBHandler(this);
        String voterEmail = sharedPreferences.getString(DBHandler.getColumnEmail(), "");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

                int index1 = positionOrder.indexOf(position1);
                int index2 = positionOrder.indexOf(position2);

                if (index1 == index2) {
                    return 0;
                } else if (index1 == -1) {
                    return 1;
                } else if (index2 == -1) {
                    return -1;
                } else {
                    return Integer.compare(index1, index2);
                }
            }
        };

        Collections.sort(candidateList, positionComparator);
        candidateAdapter = new CandidateAdapter(this, candidateList);
        recyclerView.setAdapter(candidateAdapter);
        dbHandler.updateHasVoted(voterEmail);
    }
}