package com.example.eleksayon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private List<Candidate> candidateList;
    private Context context;
    private List<String> votedPositions;
    private DBHandler dbHandler;

    public CandidateAdapter(Context context, List<Candidate> candidateList) {
        this.context = context;
        this.candidateList = candidateList;
        this.votedPositions = new ArrayList<>();
        this.dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_infocard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Candidate candidate = candidateList.get(position);
        holder.candidateName.setText(candidate.getFirstName() + " " + candidate.getLastName());
        holder.candidatePosition.setText(candidate.getPosition());
        holder.candidateDescription.setText(candidate.getDescription());
        Bitmap bitmap = BitmapFactory.decodeFile(candidate.getImagePath());
        holder.candidateImage.setImageBitmap(bitmap);
        if (votedPositions.contains(candidate.getPosition())) {
            holder.voteButton.setEnabled(false);
            Toast.makeText(context, "You have already voted for this position.", Toast.LENGTH_SHORT).show();
        } else {
            holder.voteButton.setEnabled(true);

            holder.voteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Candidate votedCandidate : candidateList) {
                        if (votedCandidate.getPosition().equals(candidate.getPosition()) && votedPositions.contains(votedCandidate.getPosition())) {
                            Toast.makeText(context, "You can only vote for one candidate per position.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    votedPositions.add(candidate.getPosition());
                    holder.voteButton.setEnabled(false);
                    dbHandler.incrementVoteCount(candidate.getId());
                    Toast.makeText(context, "Vote counted successfully.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView candidateImage;
        TextView candidateName;
        TextView candidatePosition;
        TextView candidateDescription;
        Button voteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidateImage = itemView.findViewById(R.id.uploadedImage2);
            candidateName = itemView.findViewById(R.id.candidate_name_1);
            candidatePosition = itemView.findViewById(R.id.candidate_position_1);
            candidateDescription = itemView.findViewById(R.id.candidate_description_1);
            voteButton = itemView.findViewById(R.id.vote_button_1);
        }
    }
}