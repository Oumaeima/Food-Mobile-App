package com.example.myfoodapplication.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Activity.Responsable;
import com.example.myfoodapplication.Category.Adapter.MainAdapter;
import com.example.myfoodapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView ;
    MainAdapter adapter  ;
    FloatingActionButton floatingActionButton ;
    ImageView returnIcon4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2 );

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager( new LinearLayoutManager(MainActivity2.this));

        toolbar = findViewById(R.id.toolbarCat);
        setSupportActionBar(toolbar);

       // returnIcon4 = findViewById(R.id.returnIcon4);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, Responsable.class));
            }
        });


        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category"), MainModel.class)
                        .build();

        adapter = new MainAdapter(options);
        recyclerView.setAdapter(adapter);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,AddActivity.class));
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
        SearchView  searchView = (SearchView)item.getActionView();

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

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category").orderByChild("name").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();

        adapter  = new MainAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}