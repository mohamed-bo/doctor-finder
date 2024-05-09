package com.doctor.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.doctor.doctorapp.classes.MedicinesReminder;
import com.doctor.doctorapp.adapter.alarmMedicineAdapter;
import com.doctor.doctorapp.classes.time;
import com.doctor.doctorapp.alarmnotification.AlarmReciever;
import com.doctor.doctorapp.dialogs.TimePickerFragment;
import com.doctor.doctorapp.fragment.MedicineReminderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class createMedicineReminder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    EditText nameMedicine;
    EditText quantity;
    EditText note;
    Button addAlarm;
    int position;
    public static ArrayList<time> alarms;
    public static alarmMedicineAdapter mListAdapter;
    MedicinesReminder bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medicine_reminder);
        alarms = new ArrayList<>();
        mListAdapter = new alarmMedicineAdapter();

        mListAdapter.setData(alarms);
        RecyclerView list_time = (RecyclerView) findViewById(R.id.list_alarm);
        list_time.setLayoutManager(new LinearLayoutManager(this));
        list_time.setAdapter(mListAdapter);
        addAlarm = (Button) findViewById(R.id.addTime);
        nameMedicine = (EditText) findViewById(R.id.name_createMedicine);
        quantity = (EditText) findViewById(R.id.quantity_createMedicine);
        note = (EditText) findViewById(R.id.note_createMedicine);
        DialogFragment time = new TimePickerFragment(this);
        Intent intent = getIntent();
        String action = intent.getStringExtra("action");
        if(action.equals("edit")){
            position = intent.getIntExtra("position",0);
            bean = MedicineReminderFragment.Medicines.get(position);
            nameMedicine.setText(bean.getNameOfMedicine());
            quantity.setText(bean.getQuantityOfMedicine());
            note.setText(bean.getNote());
            alarms = bean.times;
            mListAdapter.setData(alarms);
        }

        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.show(getSupportFragmentManager(), "timePicker");
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_createMedicine);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nameMedicine.getText().toString().equals("")){

                MedicinesReminder newMedicinesReminder = new MedicinesReminder();
                newMedicinesReminder.setNameOfMedicine(nameMedicine.getText().toString());
                newMedicinesReminder.setQuantityOfMedicine(quantity.getText().toString());
                newMedicinesReminder.setNote(note.getText().toString());
                newMedicinesReminder.setTimes(alarms);
                if(action.equals("new")){
                    MedicineReminderFragment.Medicines.add(0, newMedicinesReminder);
                }
                else if(action.equals("edit")){
                    cancelAlarms(MedicineReminderFragment.Medicines.get(position).times);
                    MedicineReminderFragment.Medicines.add(position, newMedicinesReminder);
                    MedicineReminderFragment.Medicines.remove(position+1);
                }
                MedicineReminderFragment.mListAdapter.setData(MedicineReminderFragment.Medicines);
                    startAlarmList();
                MedicineReminderFragment.saveInstance();
                finish();}
                else{
                    Toast.makeText(getApplicationContext(),"Please write the name of medicine",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        alarms.add(new time(hourOfDay,minute));
        mListAdapter.setData(alarms);
    }


    private void cancelAlarms(ArrayList<time> times){
        for(int i=0;i<times.size();i++) {
            AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, times.get(i).getId(), intent, 0);
            alarm.cancel(pendingIntent);
        }
    }

    private void startAlarmList(){
        for(int j=0;j<alarms.size();j++){
            AlarmManager alarm = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReciever.class);
            intent.putExtra("name", nameMedicine.getText().toString());
            intent.putExtra("note", note.getText().toString());
            intent.putExtra("time", "");
            intent.putExtra("quantity", quantity.getText().toString());
            intent.putExtra("type", "medicine");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,alarms.get(j).getId(),intent,0);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.set(Calendar.HOUR_OF_DAY, alarms.get(j).getHour());
            c.set(Calendar.MINUTE, alarms.get(j).getMinute());
            c.set(Calendar.SECOND,00);
            long startUpTime = c.getTimeInMillis();
            if (System.currentTimeMillis() > startUpTime) {
                startUpTime = startUpTime + 24*60*60*1000;
            }
            alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }
}
