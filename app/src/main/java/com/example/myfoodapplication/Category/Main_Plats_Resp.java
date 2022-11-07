package com.example.myfoodapplication.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapplication.Activity.Ajout_Plats;
import com.example.myfoodapplication.Activity.Responsable;
import com.example.myfoodapplication.Category.Adapter.PlatResAdapter;
import com.example.myfoodapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Main_Plats_Resp extends AppCompatActivity {

    RecyclerView recyclerView ;
    PlatResAdapter adapter;
    FloatingActionButton floatingActionButton ;
    ImageView returnIcon3;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plats_resp );

        recyclerView = findViewById(R.id.recycler2);
        recyclerView.setLayoutManager( new LinearLayoutManager(Main_Plats_Resp.this));
        floatingActionButton = findViewById(R.id.addbtn);

        toolbar = findViewById(R.id.toolbarFood);
        setSupportActionBar(toolbar);



       //returnIcon3 = findViewById(R.id.returnF);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Plats_Resp.this, Responsable.class));
            }
        });

        FirebaseRecyclerOptions<PlatRespModel> options = new FirebaseRecyclerOptions.Builder<PlatRespModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Plat")
        , PlatRespModel.class).build();

        adapter = new PlatResAdapter(options);
        recyclerView.setAdapter(adapter);

       floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Plats_Resp.this, Ajout_Plats.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }


        });

        return super.onCreateOptionsMenu(menu);
    }

    private  void txtSearch (String str ) {
        FirebaseRecyclerOptions<PlatRespModel> options =
                new FirebaseRecyclerOptions.Builder<PlatRespModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Plat").orderByChild("name").startAt(str).endAt(str+"~"), PlatRespModel.class)
                        .build();

        adapter  = new PlatResAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
