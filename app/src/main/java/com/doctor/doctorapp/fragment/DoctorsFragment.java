package com.doctor.doctorapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doctor.doctorapp.ClassifierActivity;
import com.doctor.doctorapp.classes.Doctor;
import com.doctor.doctorapp.adapter.ListDoctorAdapter;
import com.doctor.doctorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iammert.library.ui.multisearchviewlib.MultiSearchView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DoctorsFragment extends Fragment {
    int iindex;
    boolean onSearch;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final static String STORE_FILE_NAME = "STORE_Doctors";
    private static ListDoctorAdapter mListAdapter;
    public static List<Doctor> Doctors = new ArrayList<>();
    public DoctorsFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        sharedPreferences = getActivity().getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Doctors = getList();
        mListAdapter = new ListDoctorAdapter(false);
        mListAdapter.setData(Doctors);
        RecyclerView list_doctors = (RecyclerView) root.findViewById(R.id.list_doctors);
        list_doctors.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list_doctors.setAdapter(mListAdapter);

        ImageView filter = (ImageView) root.findViewById(R.id.filter);
        MultiSearchView multiSearchView = root.findViewById(R.id.multiSearchView);
        String[] speciality = {"All","Gastroenterologist", "Infectious Disease Physician", "Nephrologist", "Ophthalmologist", "Otolaryngologist", "Pulmonologist", "Neurologist", "Family Physician", "Surgeon", "Pediatrician"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Speciality");
        builder.setItems(speciality, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!speciality[which].equals("All")) {
                    onSearch(speciality[which]);
                }
                else {
                    onSearch("");
                }
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
        multiSearchView.setSearchViewListener(new MultiSearchView.MultiSearchViewListener() {
            @Override
            public void onTextChanged(int index, CharSequence s) {
                onSearch(s);
            }

            @Override
            public void onSearchComplete(int index, CharSequence s) {
                onSearch=true;
                iindex=index;
            }

            @Override
            public void onSearchItemRemoved(int index) {
                if(iindex==index){
                notifyDataChanged();
            }}

            @Override
            public void onItemSelected(int index, CharSequence s) {
                onSearch=true;
                iindex=index;
                onSearch(s);
            }
        });
        FloatingActionButton fab = root.findViewById(R.id.fab_hand_gesture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassifierActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
    public static void saveInstance(){
        setList(STORE_FILE_NAME,Doctors);
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
    public List<Doctor> getList(){
        List<Doctor> doctorList;
        String serializedObject = sharedPreferences.getString(STORE_FILE_NAME, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Doctor>>(){}.getType();
            doctorList = gson.fromJson(serializedObject, type);

        }
        else {
            doctorList = new ArrayList<>();
            doctorList.add(new Doctor("Dr. Yahia Abd El Wahed",0,"0674713580","Batna","35.531830, 6.188487","Gastroenterologist",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Ayoub Abd El Wahed ",0,"0674713581","Batna","35.531830, 6.188487","Gastroenterologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. zakariya Abd El Wahed ",0,"0674713582","Batna","35.531830, 6.188487","Infectious Disease Physician",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Younes Abd El Wahed ",0,"0674713583","Batna","35.531830, 6.188487","Ophthalmologist",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Yousef Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Pulmonologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Moussa Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Gastroenterologist",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Aisa Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Eyes Doctor",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Nouh Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Nephrologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Adam Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Pulmonologist",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Mohamed Abderrahmen",0,"0674713580","Batna","35.531830, 6.188487","Gastroenterologist",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Mohamed madani",0,"0674713580","Batna","35.531830, 6.188487","Otolaryngologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Mohamed abdellah",0,"0674713580","Batna","35.531830, 6.188487","Neurologist",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Mohamed Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Infectious Disease Physician",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Mohamed Abd El ajabar",0,"0674713580","Batna","35.531830, 6.188487","Otolaryngologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Mohamed Abd ennour",0,"0674713580","Batna","35.531830, 6.188487","Nephrologist",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Nouh Abd Abderrahmen ",0,"0674713580","Batna","35.531830, 6.188487","Pediatrician",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Adam Abd ennour ",0,"0674713580","Batna","35.531830, 6.188487","Pediatrician",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Massoudi Abd ennour",0,"0674713580","Batna","35.531830, 6.188487","Surgeon",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Naserddine madani",0,"0674713580","Batna","35.531830, 6.188487","Otolaryngologist",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Moussa Abderrahmen",0,"0674713580","Batna","35.531830, 6.188487","Surgeon",R.drawable.doctorc));
            doctorList.add(new Doctor("Dr. Badreddine Abd El Wahed ",0,"0674713580","Batna","35.531830, 6.188487","Family Physician",R.drawable.doctora));
            doctorList.add(new Doctor("Dr. Aymen Redha",0,"0674713580","Batna","35.531830, 6.188487","Family Physician",R.drawable.doctorb));
            doctorList.add(new Doctor("Dr. Hayder Abd ennour",0,"0674713580","Batna","35.531830, 6.188487","Neurologist",R.drawable.doctorc));
             }
        return doctorList;
    }
public static void notifyDataChanged(){
        mListAdapter.setData(Doctors);
}
public void onSearch(CharSequence s){
    List<Doctor> doctorList= new ArrayList<>();
        for(int i =0; i<Doctors.size();i++){
            if (Doctors.get(i).getName().toLowerCase().contains(s.toString().toLowerCase())||Doctors.get(i).getSpeciality().toLowerCase().contains(s.toString().toLowerCase())){
                doctorList.add(Doctors.get(i));
            }
        }
        mListAdapter.setData(doctorList);
}

}
