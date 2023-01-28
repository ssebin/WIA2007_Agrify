package com.example.agrifymad.adapters;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrifymad.EventDetail_four;
import com.example.agrifymad.EventDetail_one;
import com.example.agrifymad.EventDetail_three;
import com.example.agrifymad.EventDetail_two;
import com.example.agrifymad.models.EventModel;
import com.example.agrifymad.R;

import java.util.ArrayList;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {
    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();

    public EventsRecyclerViewAdapter(ArrayList<EventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_thumb, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(eventModelArrayList.get(position).getDay());
        holder.month.setText(eventModelArrayList.get(position).getMonth());
        holder.title.setText(eventModelArrayList.get(position).getTitle());
        Glide.with(holder.imageView).load(eventModelArrayList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView date, month, title;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            title = itemView.findViewById(R.id.eventTitle);
            imageView = itemView.findViewById(R.id.card_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position == 0) {
                Intent intent = new Intent(view.getContext(), EventDetail_one.class);
                view.getContext().startActivity(intent);
            }
            if (position == 1) {
                Intent intent = new Intent(view.getContext(), EventDetail_two.class);
                view.getContext().startActivity(intent);
            }
            if (position == 2) {
                Intent intent = new Intent(view.getContext(), EventDetail_three.class);
                view.getContext().startActivity(intent);
            }
            if (position == 3) {
                Intent intent = new Intent(view.getContext(), EventDetail_four.class);
                view.getContext().startActivity(intent);
            }
        }
    }
}