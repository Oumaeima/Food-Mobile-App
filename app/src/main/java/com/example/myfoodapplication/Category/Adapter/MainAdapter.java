package com.example.myfoodapplication.Category.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myfoodapplication.Category.MainModel;
import com.example.myfoodapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainAdapter extends FirebaseRecyclerAdapter <MainModel,MainAdapter.myViewHolder>  {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder  holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {
        holder.name.setText(model.getName());
        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus  dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder( new ViewHolder(R.layout.update))
                        .setExpanded(true,1200 )
                        .create();

                //dialogPlus.show();
                View  view1 = dialogPlus.getHolderView() ;
                EditText name = view1.findViewById(R.id.txtName);
                EditText image = view1.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view1.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                image.setText(model.getImage());

                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String , Object> map = new HashMap<>();
                        map.put("name" ,name.getText().toString() );
                        map.put("image" , image.getText().toString() );

                        FirebaseDatabase.getInstance().getReference().child("category")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), " Data Updated Successfully" , Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(holder.name.getContext(), "Error while Updating " , Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });






            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you  Sure ? ");
                builder.setMessage("Deleted data can't be Undo ");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("category").child(getRef(position).getKey()).removeValue() ;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled" , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show() ;

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_iteam,parent ,false);
        return  new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img ;
        TextView name ;

        Button btnEdit,btnDelete ;

        public myViewHolder(@NonNull View itemView){

            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.nametext);
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }





}


