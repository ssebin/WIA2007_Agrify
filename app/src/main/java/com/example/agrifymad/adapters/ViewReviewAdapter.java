package com.example.agrifymad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrifymad.R;
import com.example.agrifymad.models.ViewReviewModel;

import java.util.List;

public class ViewReviewAdapter extends RecyclerView.Adapter<ViewReviewAdapter.ViewHolder>{

    Context context;
    List<ViewReviewModel> list;


    public ViewReviewAdapter(Context context, List<ViewReviewModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewReviewAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.rating.setText(list.get(position).getRating());
        holder.description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,rating,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.review_details_img);
            name = itemView.findViewById(R.id.review_details_name);
            rating = itemView.findViewById(R.id.review_details_rating);
            description = itemView.findViewById(R.id.review_details);


        }
    }
}
