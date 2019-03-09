package com.prophet.drconnect.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    private RecyclerView recyclerView;
    private DoctorsAdapter adapter;
    private List<Doctors> list;

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
        getActivity().setTitle(R.string.nav_menu_doctors);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        adapter = new DoctorsAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpTopixel(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareDoctors();

        return view;
    }

    private void prepareDoctors() {
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
        Doctors doctor = new Doctors("Dr. Tom Cruise", "FCPS", "Medicine", covers[0]);
        list.add(doctor);
        doctor = new Doctors("Dr. Tom Hardy", "MBBS", "Cardiac", covers[1]);
        list.add(doctor);
        doctor = new Doctors("Dr. Hillery Clinton", "FCPS", "Ontropologist", covers[2]);
        list.add(doctor);
        doctor = new Doctors("Dr. Angelina Jolly", "FCPS", "Medicine", covers[3]);
        list.add(doctor);
        doctor = new Doctors("Dr. Sheha Lubna", "FCPS", "Medicine", covers[4]);
        list.add(doctor);
        doctor = new Doctors("Dr. Farhin Khandkar", "FCPS", "Medicine", covers[5]);
        list.add(doctor);
        doctor = new Doctors("Dr. Mehedi Hasan", "FCPS", "Medicine", covers[6]);
        list.add(doctor);
        doctor = new Doctors("Dr. T M Haque", "FCPS", "Medicine", covers[7]);
        list.add(doctor);
        doctor = new Doctors("Dr. Vendetta", "FCPS", "Medicine", covers[8]);
        list.add(doctor);

        adapter.notifyDataSetChanged();
    }

    private int dpTopixel(int dp) {
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
}
