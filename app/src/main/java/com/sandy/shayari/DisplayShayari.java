package com.sandy.shayari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayShayari extends AppCompatActivity {

    private RecyclerView recyclerView;
    CategoriesAdapter adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_shayari);

        Bundle bundle = getIntent().getExtras();
        String ref = bundle.getString("key");

        FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase
                .getInstance("https://shayari-ef911-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Categories").child(ref).child("shayari");

        recyclerView = findViewById(R.id.recycleView);

//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<CategoriesModel> options =
                new FirebaseRecyclerOptions.Builder<CategoriesModel>()
                        .setQuery(databaseReference,CategoriesModel.class)
                        .build();

        adapter = new CategoriesAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        adapter.startListening();
        super.onStop();
    }
}