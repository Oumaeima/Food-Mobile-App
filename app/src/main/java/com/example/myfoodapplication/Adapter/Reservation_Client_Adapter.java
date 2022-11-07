package com.example.myfoodapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Model.HomeModel;
import com.example.myfoodapplication.Model.ReservationModel;
import com.example.myfoodapplication.R;
import java.util.List;


public class Reservation_Client_Adapter extends RecyclerView.Adapter<Reservation_Client_Adapter.ReservationViewHolder>{

    Context context;
    List<ReservationModel> list;

    public Reservation_Client_Adapter(Context context, List<ReservationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Reservation_Client_Adapter.ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reservation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Reservation_Client_Adapter.ReservationViewHolder holder, int position) {

        ReservationModel reservationModel = list.get(position);
        holder.phone.setText(reservationModel.getUser_phone());
        holder.date.setText(reservationModel.getDate());
        holder.personNbr.setText(reservationModel.getPersonNbr());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView phone, personNbr, date;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.twtPhone);
            personNbr = itemView.findViewById(R.id.textNb);
            date = itemView.findViewById(R.id.dateTxt);
        }
    }
}
