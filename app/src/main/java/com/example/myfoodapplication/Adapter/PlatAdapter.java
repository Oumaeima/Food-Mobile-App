package com.example.myfoodapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myfoodapplication.Activity.FoodDetails;
import com.example.myfoodapplication.Model.Plat;
import com.example.myfoodapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;


public class PlatAdapter extends RecyclerView.Adapter<PlatAdapter.MyViewHolder>{
    Context context;
    ArrayList<Plat> list;
    FirebaseUser user;

    String userId;


    public PlatAdapter(Context context, ArrayList<Plat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewholder_plat, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Plat plat = list.get(position);

        Glide.with(holder.img.getContext())
                .load(plat.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.name.setText(plat.getName());
        holder.price.setText(plat.getPrice());

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetails.class);
                intent.putExtra("id", plat.getId());
                intent.putExtra("name", plat.getName());
                intent.putExtra("price", plat.getPrice());
                intent.putExtra("image", plat.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img, addPic;
        TextView name, price;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.PlatPic);
            name = itemView.findViewById(R.id.PlatName);
            price = itemView.findViewById(R.id.PlatPrice);
            constraintLayout = itemView.findViewById(R.id.mainLayout);


        }
    }

}
