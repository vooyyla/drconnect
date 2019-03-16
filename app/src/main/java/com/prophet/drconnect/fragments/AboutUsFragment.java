package com.prophet.drconnect.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prophet.drconnect.R;
import com.prophet.drconnect.adapters.AboutUsAdapter;
import com.prophet.drconnect.models.AboutUs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUsFragment extends Fragment {

    private final static String LOG_TAG = AboutUsFragment.class.getName();
    private AboutUsAdapter adapter;
    private List<AboutUs> list;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;



    public AboutUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AboutUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.nav_menu_about_us);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(LOG_TAG, "creating view");
        View view = inflater.inflate(R.layout.blank_fragment, null);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.v(LOG_TAG, "firebase instance ");

        list = new ArrayList<>();

        adapter = new AboutUsAdapter(getActivity(), list);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Log.v(LOG_TAG, "recyclerview created");

        ProgressBar  progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        Log.v(LOG_TAG, "progress is now invisible");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        Log.v(LOG_TAG, "layoutmanager incoming");
        recyclerView.setLayoutManager(layoutManager);
        Log.v(LOG_TAG, "layoutmanager attached");
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        Log.v(LOG_TAG, "view got an adapter");

        canYouhearMe();
        Log.v(LOG_TAG, "canyouhereme fired");
        return view;
    }

    private void canYouhearMe() {
        databaseReference = databaseReference.child("about_us");
        Log.v(LOG_TAG, "dir://root/about_us/");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String header = snapshot.child("header").getValue(String.class);
                    Log.v(LOG_TAG, "got the head");
                    String body = snapshot.child("body").getValue(String.class);
                    Log.v(LOG_TAG, "and body");
                    String footer = snapshot.child("footer").getValue(String.class);
                    Log.v(LOG_TAG, "footer");

                    AboutUs aboutUs = new AboutUs(header, body, footer);
                    Log.v(LOG_TAG, "created signboard content");
                    list.add(aboutUs);
                    Log.v(LOG_TAG, "content sent");
                    adapter.notifyDataSetChanged();
                    Log.v(LOG_TAG, "adapter change notified");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(eventListener);
        Log.v(LOG_TAG, "eventlistener attached to database");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
