package com.prophet.drconnect.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prophet.drconnect.R;
import com.prophet.drconnect.adapters.ServicesAdapter;
import com.prophet.drconnect.models.Services;
import com.prophet.drconnect.utilities.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private final static String LOG_TAG = ServicesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Services> servicesList = new ArrayList<>();
    private ServicesAdapter adapter;

    public ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.nav_menu_services);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.blank_fragment, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new ServicesAdapter(getActivity(), servicesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(adapter);

        serviceData();
        return view;

    }

    private void serviceData(){
        int[] covers = new int[] {
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11
        };
        Services service = new Services(covers[1], "Cure Home general hospital", "Diagnosis, Therapy");
        servicesList.add(service);

        service = new Services(covers[2], "Ad-din specialized hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[3], "Square hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[4], "Apollo hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[5], "Dhaka medical college hospital", "Therapy");
        servicesList.add(service);

        service = new Services(covers[6], "Khulna medical college hospital", "Therapy");
        servicesList.add(service);

        service = new Services(covers[7], "Sylhet hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[8], "Ad-din specialized hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[9], "Ad-din specialized hospital", "Consultant, Therapy");
        servicesList.add(service);

        service = new Services(covers[10], "Ad-din specialized hospital", "Consultant, Therapy");
        servicesList.add(service);



    }

}
