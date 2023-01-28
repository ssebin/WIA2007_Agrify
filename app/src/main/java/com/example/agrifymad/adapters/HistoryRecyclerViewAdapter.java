package com.example.agrifymad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrifymad.models.HistoryModel;
import com.example.agrifymad.R;
import com.example.agrifymad.RateUsDialog;

import java.util.ArrayList;


public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {
    ArrayList<HistoryModel> historyModelArrayList = new ArrayList<>();

    public HistoryRecyclerViewAdapter(ArrayList<HistoryModel> historyModelArrayList) {
        this.historyModelArrayList = historyModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_thumb, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(historyModelArrayList.get(position).getTitle());
        holder.status.setText(historyModelArrayList.get(position).getStatus());
        holder.cost.setText(historyModelArrayList.get(position).getCost());
        holder.date.setText(historyModelArrayList.get(position).getDate());
        Glide.with(holder.imageView).load(historyModelArrayList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return historyModelArrayList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, status, cost, date;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.historyTitle);
            status = itemView.findViewById(R.id.historyStatus);
            cost = itemView.findViewById(R.id.historyCost);
            date = itemView.findViewById(R.id.historyDate);

            imageView = itemView.findViewById(R.id.card_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            RateUsDialog rateUsDialog = new RateUsDialog(view.getContext());
            rateUsDialog.getWindow();
            rateUsDialog.setCancelable(false);
            rateUsDialog.show();
        }
    }
}