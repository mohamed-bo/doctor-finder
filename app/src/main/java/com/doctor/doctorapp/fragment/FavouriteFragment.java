package com.doctor.doctorapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctor.doctorapp.classes.Doctor;
import com.doctor.doctorapp.adapter.ListDoctorAdapter;
import com.doctor.doctorapp.R;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {

    private static ListDoctorAdapter mListAdapter;
    public static ArrayList<Doctor> Favourite = new ArrayList<>();
    public FavouriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        mListAdapter = new ListDoctorAdapter(true);
        getFavourite();
        mListAdapter.setData(Favourite);
        RecyclerView list_doctors = (RecyclerView) root.findViewById(R.id.list_doctors);
        list_doctors.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list_doctors.setAdapter(mListAdapter);
        return root;
    }

    public static void getFavourite() {
        Favourite = new ArrayList<>();
        for(int i = 0;i<DoctorsFragment.Doctors.size();i++){
            if(DoctorsFragment.Doctors.get(i).isFavorite()){
                Favourite.add(DoctorsFragment.Doctors.get(i));
            }
        }
    }

    public static void notifyDataAdd(Doctor bean){
        getFavourite();
    mListAdapter.setData(Favourite);
}
    public static void notifyDataRemove(Doctor bean){
        getFavourite();
        mListAdapter.setData(Favourite);
    }

}
