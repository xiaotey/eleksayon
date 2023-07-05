package com.example.eleksayon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleksayon.Candidate;

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
        holder.voteCount.setText(String.valueOf(candidate.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView voteCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            voteCount = itemView.findViewById(R.id.voteCount);
        }
    }
    public void updateVoteCount(int position, int voteCount) {
        Candidate candidate = candidateList.get(position);
        candidate.setVoteCount(voteCount);
        notifyDataSetChanged();
    }

}
