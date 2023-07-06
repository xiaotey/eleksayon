package com.example.eleksayon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class ViewPageAdapter extends RecyclerView.Adapter<ViewPageAdapter.ViewHolder> {

    private List<Candidate> candidateList;

    public ViewPageAdapter(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_page_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Candidate candidate = candidateList.get(position);
        // Set other view values
        holder.candidateName.setText(candidate.getFirstName() + " " + candidate.getLastName());
        holder.candidatePosition.setText(candidate.getPosition());
        holder.candidateDescription.setText(candidate.getDescription());
        holder.voteCount.setText(String.valueOf(candidate.getVoteCount()));
        Bitmap bitmap = BitmapFactory.decodeFile(candidate.getImagePath());
        holder.candidateImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView candidateName;
        TextView candidatePosition;
        TextView candidateDescription;
        TextView voteCount;
        ImageView candidateImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidateImage = itemView.findViewById(R.id.uploadedImage2);
            candidateName = itemView.findViewById(R.id.candidate_name_1);
            candidatePosition = itemView.findViewById(R.id.candidate_position_1);
            candidateDescription = itemView.findViewById(R.id.candidate_description_1);
            voteCount = itemView.findViewById(R.id.voteCount);
        }
    }

}
