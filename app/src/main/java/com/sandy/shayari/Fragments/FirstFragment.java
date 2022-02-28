package com.sandy.shayari.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sandy.shayari.FirstFragment.DataAdapter;
import com.sandy.shayari.FirstFragment.DataModel;
import com.sandy.shayari.R;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    RecyclerView recyclerView;
    DataAdapter adapter;
    private ArrayList<String> random_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        random_list = new ArrayList<>();
        FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference database = FirebaseDatabase.getInstance("https://shayari-ef911-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Categories");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot sub1 : datasnapshot.getChildren()){
                    if(sub1.exists()){
                        for (DataSnapshot sub2: sub1.getChildren()) {
                            if (sub2.exists()){
                                for (DataSnapshot sub3: sub2.getChildren()) {
                                    if(sub3.exists()){
                                        DataModel dataModel = sub3.getValue(DataModel.class);
                                        assert dataModel != null;
                                        random_list.add(dataModel.getData());
                                    }
                                }
                            }
                        }
                    }else{
                        Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                    adapter = new DataAdapter(random_list);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}