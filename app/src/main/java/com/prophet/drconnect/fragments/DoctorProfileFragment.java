package com.prophet.drconnect.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prophet.drconnect.R;
import com.prophet.drconnect.adapters.DoctorProfileAdapter;
import com.prophet.drconnect.models.Doctors;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorProfileFragment extends Fragment {


    private String ID, name, specialty, degree, thumbnail, departments, bio;

    private DatabaseReference databaseReference;
    private ValueEventListener listener;
    private DoctorProfileAdapter adapter;
    private List<Doctors> list;
    public DoctorProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Profile");
        Bundle bundle = getArguments();
        if (bundle != null) {
            ID = (String) bundle.getSerializable("id");
            name = (String) bundle.getSerializable("name");
            specialty = (String) bundle.getSerializable("specialty");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        list = new ArrayList<>();
        View view = inflater.inflate(R.layout.blank_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new DoctorProfileAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        asyncTask();
        return view;
    }

    private void asyncTask(){
        databaseReference = databaseReference.child("doctors").child(ID);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                degree = dataSnapshot.child("degree").getValue(String.class);
                departments = dataSnapshot.child("departments").getValue(String.class);
                bio = dataSnapshot.child("bio").getValue(String.class);
                thumbnail = dataSnapshot.child("thumbnail").getValue(String.class);
                Doctors doctors = new Doctors(name, degree, departments, specialty, thumbnail, bio);
                list.add(doctors);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(listener);
    }

}
