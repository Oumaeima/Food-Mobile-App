package com.example.myfoodapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodapplication.Model.catRecommanded;
import com.example.myfoodapplication.R;

import java.util.List;

public class catAdapter extends RecyclerView.Adapter<catAdapter.catViewHolder> {
    List<catRecommanded> list;
    Context context;

    public catAdapter(List<catRecommanded> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public catViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new catViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommanded, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull catViewHolder holder, int position) {

        catRecommanded catRecommanded = list.get(position);
        Glide.with(holder.imageView.getContext())
                .load(catRecommanded.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class catViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public catViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgRec);
        }
    }
}
