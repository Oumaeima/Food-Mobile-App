package com.example.myfoodapplication.Order_Responsable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapplication.R;
import java.util.List;

public class OrderAdapterRes extends RecyclerView.Adapter<OrderAdapterRes.OrderViewHolder>{

    List<OrderR> list;
    Context context;

    public OrderAdapterRes(List<OrderR> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_res_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderR order = list.get(position);
        holder.name.setText(order.getName());
        holder.price.setText(order.getItemprice());
        holder.quantity.setText(order.getQuantity());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you  Sure ? ");
                builder.setMessage("Deleted data can't be Undo ");
                builder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "REFUSE" , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show() ;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView name, price,quantity;
        ConstraintLayout constraintLayout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtn);
            price = itemView.findViewById(R.id.PriceTxt);
            quantity = itemView.findViewById(R.id.txtquantity);
            constraintLayout = itemView.findViewById(R.id.orderConstraint);
        }
    }
}
