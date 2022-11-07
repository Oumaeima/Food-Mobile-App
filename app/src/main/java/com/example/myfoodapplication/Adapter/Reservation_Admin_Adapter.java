package com.example.myfoodapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Model.ReservationModel;
import com.example.myfoodapplication.Model.ReservationModel2;
import com.example.myfoodapplication.R;
import java.util.List;


public class Reservation_Admin_Adapter extends RecyclerView.Adapter<Reservation_Admin_Adapter.Reservation2ViewHolder>{

    Context context;
    List<ReservationModel2> list;

    public Reservation_Admin_Adapter(Context context, List<ReservationModel2> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public Reservation_Admin_Adapter.Reservation2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Reservation2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reservation2_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Reservation_Admin_Adapter.Reservation2ViewHolder holder, int position) {

        ReservationModel2 reservationModel = list.get(position);
        holder.phone.setText(reservationModel.getUser_phone());
        holder.date.setText(reservationModel.getDate());
        holder.personNbr.setText(reservationModel.getPersonNbr());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Reservation2ViewHolder extends RecyclerView.ViewHolder {
        TextView phone, personNbr, date;
        public Reservation2ViewHolder(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.twtPhone2);
            personNbr = itemView.findViewById(R.id.textNb2);
            date = itemView.findViewById(R.id.dateTxt2);
        }
    }
}
