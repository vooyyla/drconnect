package com.prophet.drconnect.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prophet.drconnect.R;
import com.prophet.drconnect.adapters.DoctorsAdapter;
import com.prophet.drconnect.models.Doctors;
import com.prophet.drconnect.utilities.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorsFragment extends Fragment {

    private List<Doctors> list;
    private DoctorsAdapter adapter;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DoctorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorsFragment newInstance() {
       return new DoctorsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.nav_menu_doctors);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fragment, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        list = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        adapter = new DoctorsAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPixel(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        asynctask();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (search != null){
            searchView = (SearchView) search.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d("onQueryTextSubmit", s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d("onQueryTextChange", s);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        inflater.inflate(R.menu.filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                return false;
                default:
                    break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    private int dpToPixel(int dptopixel) {
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dptopixel, resources.getDisplayMetrics()));
    }

    private void asynctask() {
        databaseReference = databaseReference.child("doctors");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String specialty = snapshot.child("specialty").getValue(String.class);
                    String thumbnail = snapshot.child("thumbnail").getValue(String.class);
                    Doctors doctor = new Doctors(name, specialty,thumbnail);
                    list.add(doctor);
                    adapter.notifyDataSetChanged();
                    Log.d(name, specialty);
                    Log.d("DoctorsFragment", "list added");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

}
