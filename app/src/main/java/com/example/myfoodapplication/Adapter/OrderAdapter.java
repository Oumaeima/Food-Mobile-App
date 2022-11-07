package com.example.myfoodapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapplication.Model.Order;
import com.example.myfoodapplication.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    List<Order> list;
    Context context;

    public OrderAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = list.get(position);
        holder.name.setText(order.getName());
        holder.price.setText(order.getItemprice());
        holder.quantity.setText(order.getQuantity());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView name, price,quantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtn);
            price = itemView.findViewById(R.id.PriceTxt);
            quantity = itemView.findViewById(R.id.txtquantity);
        }
    }
}
