package com.example.myfoodapplication.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Activity.CartActivity;
import com.example.myfoodapplication.Model.CartModel;
import com.example.myfoodapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<CartModel> list;
    Context context;
    int pQuantity = 1;
    public String _subtotal, _price, _quantity;
    CartActivity cartActivity;
    FirebaseAuth auth;

    DatabaseReference database;

    public CartAdapter(List<CartModel> list, Context context) {
        this.list = list;
        this.context = context;

        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {

        database = FirebaseDatabase.getInstance().getReference("Cart");
        CartModel cartModel = list.get(position);
        holder.name.setText(cartModel.getName());
        _price = cartModel.getPrice();
        holder.price.setText(_price);

        _quantity = cartModel.getQuantity();
        holder.quantity.setText(_quantity);

       /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                database.child(list.get(position).getId()).removeValue();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                Gson gson = new Gson();
                String cartStr = gson.toJson(list);
                Log.d("CART", cartStr);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

      TextView name, price,quantity;
      ImageView delete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nametxt);
            price = itemView.findViewById(R.id.PriceTxt);
            quantity = itemView.findViewById(R.id.quantity);
            delete = itemView.findViewById(R.id.delete);



        }

    }
}
