package com.sandy.shayari.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sandy.shayari.R;
import com.sandy.shayari.SecondFragment.DataAdapter;
import com.sandy.shayari.SecondFragment.DataModel;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    DataAdapter adapter;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase
                .getInstance("https://shayari-ef911-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Categories");

        recyclerView = view.findViewById(R.id.recyclerViews);

//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(databaseReference,DataModel.class)
                        .build();

        adapter = new DataAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
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