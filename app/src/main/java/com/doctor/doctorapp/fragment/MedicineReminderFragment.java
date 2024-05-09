package com.doctor.doctorapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctor.doctorapp.adapter.ListMedicinesAdapter;
import com.doctor.doctorapp.classes.MedicinesReminder;
import com.doctor.doctorapp.R;
import com.doctor.doctorapp.createMedicineReminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MedicineReminderFragment extends Fragment {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final static String STORE_FILE_NAME = "STORE_MEDICINES";
    public static ListMedicinesAdapter mListAdapter;
    public static List<MedicinesReminder> Medicines;
    public MedicineReminderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_medicine_reminder, container, false);
        mListAdapter = new ListMedicinesAdapter();
        sharedPreferences = getActivity().getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Medicines = getList();
        mListAdapter.setData(Medicines);
        RecyclerView list_doctors = (RecyclerView) root.findViewById(R.id.list_medicine);
        list_doctors.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list_doctors.setAdapter(mListAdapter);
        FloatingActionButton fab = root.findViewById(R.id.fab_medicine_fragment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), createMedicineReminder.class);
                intent.putExtra("action","new");
                startActivity(intent);
            }
        });
        return root;
    }
    public static void saveInstance(){
        setList(STORE_FILE_NAME,Medicines);
    }
    public static  <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
    public List<MedicinesReminder> getList(){
        List<MedicinesReminder> arrayMedicines;
        String serializedObject = sharedPreferences.getString(STORE_FILE_NAME, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicinesReminder>>(){}.getType();
            arrayMedicines = gson.fromJson(serializedObject, type);
            return arrayMedicines;
        }
        else {
            return new ArrayList<>();
        }
    }
}
