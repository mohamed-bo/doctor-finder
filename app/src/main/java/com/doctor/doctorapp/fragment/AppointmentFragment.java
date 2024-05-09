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

import com.doctor.doctorapp.classes.AppointmentReminder;
import com.doctor.doctorapp.adapter.ListAppontementAdapter;
import com.doctor.doctorapp.R;
import com.doctor.doctorapp.createAppointementActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class AppointmentFragment extends Fragment {
    public static ListAppontementAdapter mListAdapter;
    public static List<AppointmentReminder> Appointments = new ArrayList<>();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final static String STORE_FILE_NAME = "STORE_APPOINTMENT";
    public AppointmentFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_appointement, container, false);
        mListAdapter = new ListAppontementAdapter();
        sharedPreferences = getActivity().getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Appointments = getList();
        mListAdapter.setData(Appointments);
        RecyclerView list_doctors = (RecyclerView) root.findViewById(R.id.list_appointment);
        list_doctors.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list_doctors.setAdapter(mListAdapter);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), createAppointementActivity.class);
                intent.putExtra("action","new");
                startActivity(intent);
            }
        });
        return root;
    }
    public static void saveInstance(){
        setList(STORE_FILE_NAME,Appointments);
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
    public List<AppointmentReminder> getList(){
        List<AppointmentReminder> arrayMedicines;
        String serializedObject = sharedPreferences.getString(STORE_FILE_NAME, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<AppointmentReminder>>(){}.getType();
            arrayMedicines = gson.fromJson(serializedObject, type);
            return arrayMedicines;
        }
        else {
            return new ArrayList<>();
        }
    }



}
