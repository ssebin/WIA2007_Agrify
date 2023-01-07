package com.example.agrifymad.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrifymad.R;
import com.example.agrifymad.models.ShopModel;
import com.example.agrifymad.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    Context context;
    List<ShopModel> list;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public ShopAdapter(Context context, List<ShopModel> list) {
        this.context = context;
        this.list = list;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_shop_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.farmName.setText(list.get(position).getFarmName());
        holder.farmLocation.setText(list.get(position).getFarmLocation());
        holder.rating.setText(list.get(position).getRating());
        holder.phoneNumber.setText(list.get(position).getPhone());

        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_DIAL,  Uri.parse("tel:" + "016-7843234"));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "permission not granted", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},143);
                }else{
                    context.startActivity(intent);
                }


            }


        });

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Uri uri = Uri.parse("geo:3.12179,101.64015?q="+Uri.parse("3.12179,101.64015(Bara Farm)"));
                Intent intent = new Intent(Intent.ACTION_VIEW,  uri);
                intent.setPackage("com.google.android.apps.maps");
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "permission not granted", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},143);
                }else{
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,phone,location;
        TextView rating,farmName,farmLocation,phoneNumber;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.nav_farm_img);
            phone = itemView.findViewById(R.id.nav_farm_phone);
            location = itemView.findViewById(R.id.nav_farm_map);
            rating = itemView.findViewById(R.id.nav_farm_rating);
            farmName = itemView.findViewById(R.id.nav_farm_name);
            farmLocation = itemView.findViewById(R.id.nav_farm_location);
            phoneNumber = itemView.findViewById(R.id.nav_farm_number);

        }

    }







}
