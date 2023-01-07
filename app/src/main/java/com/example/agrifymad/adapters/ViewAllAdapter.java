package com.example.agrifymad.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrifymad.DetailedActivity;
import com.example.agrifymad.R;
import com.example.agrifymad.activities.ViewAllActivity;
import com.example.agrifymad.models.ViewAllModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;


    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {
            Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
            holder.name.setText(list.get(position).getName());
            holder.mass.setText(list.get(position).getMass());
            holder.price.setText(String.valueOf(list.get(position).getPrice()));
            holder.rating.setText(list.get(position).getRating());
            holder.farmName.setText(list.get(position).getFarmName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("detail",list.get(holder.getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,price,rating,mass,farmName;

        public  ViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            price = itemView.findViewById(R.id.view_price);
            rating= itemView.findViewById(R.id.view_rating);
            mass = itemView.findViewById(R.id.view_mass);
            farmName = itemView.findViewById(R.id.view_farmName);
        }
    }
}
