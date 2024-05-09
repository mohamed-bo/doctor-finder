package com.doctor.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.doctor.doctorapp.alarmnotification.AlarmReciever;
import com.doctor.doctorapp.classes.AppointmentReminder;
import com.doctor.doctorapp.dialogs.DatePickerFragment;
import com.doctor.doctorapp.dialogs.TimePickerFragment;
import com.doctor.doctorapp.fragment.AppointmentFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class createAppointementActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {
    EditText nameDoctor;
    EditText adresse;
    EditText note;
    Button setAlarm;
    Button setAppointementTime;
    Button setDate;
    int wichButton;
    int hourOfDayAlarm;
    int minuteAlarm;
    int hourOfDayAppointement;
    int minuteAppointement;
    int year;
    int month;
    int day;
int position;
    AppointmentReminder bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointement);
        setAlarm = (Button) findViewById(R.id.setTime_createAlarm);
        setAppointementTime = (Button) findViewById(R.id.setTime_Appointment);
        setDate = (Button) findViewById(R.id.setDate_Appointment);
        nameDoctor = (EditText) findViewById(R.id.name_createAppointment);
        adresse = (EditText) findViewById(R.id.adresse_createAppointment);
        note = (EditText) findViewById(R.id.note_createAppointment);
        DialogFragment time = new TimePickerFragment(this);
        DialogFragment date = new DatePickerFragment(this);
        Intent intent = getIntent();
        String action = intent.getStringExtra("action");
        if(action.equals("newKnown")){
            nameDoctor.setText(intent.getStringExtra("name"));
            adresse.setText(intent.getStringExtra("address"));

        }
        if(action.equals("edit")){
            position = intent.getIntExtra("position",0);
            bean = AppointmentFragment.Appointments.get(position);
            hourOfDayAlarm = bean.getHourOfDayRemind();
            minuteAlarm = bean.getMinuteRemind();
            hourOfDayAppointement = bean.getHourOfDayAppointment();
            minuteAppointement = bean.getMinuteAppointment();
            year = bean.getYear();
            month = bean.getMonth();
            day = bean.getDayOfMonth();
            setAlarm.setText(hourOfDayAlarm+":"+minuteAlarm);
            setAppointementTime.setText(hourOfDayAppointement +":"+minuteAppointement);
            setDate.setText(day+"/"+(month+1)+"/"+year);
            nameDoctor.setText(bean.getNameOfDoctor());
            adresse.setText(bean.getAdresse());
            note.setText(bean.getNote());

        }

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wichButton = 0;
                time.show(getSupportFragmentManager(), "timePicker");
            }
        });
        setAppointementTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wichButton =1;
                time.show(getSupportFragmentManager(), "timePicker");
            }
        });
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.show(getSupportFragmentManager(), "timePicker");
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab_createAppointment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nameDoctor.getText().toString().equals("")){
                MainActivity.viewPager.setCurrentItem(2);
                Calendar c = Calendar.getInstance();
                if(year==0){
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                }
                if(hourOfDayAlarm==0&&minuteAlarm==0){
                    hourOfDayAlarm = c.get(Calendar.HOUR_OF_DAY);
                    minuteAlarm = c.get(Calendar.MINUTE);
                }
                if(hourOfDayAppointement==0&&minuteAppointement==0){
                    hourOfDayAppointement = hourOfDayAlarm;
                    minuteAppointement = minuteAlarm;
                }
                c.setTimeInMillis(System.currentTimeMillis());
                c.clear();
                c.set(year,month,day,hourOfDayAlarm,minuteAlarm,0);


                    AppointmentReminder newAppoitment = new AppointmentReminder();
                    newAppoitment.setNameOfDoctor(nameDoctor.getText().toString());
                    newAppoitment.setAdresse(adresse.getText().toString());
                    newAppoitment.setNote(note.getText().toString());
                    newAppoitment.setHourOfDayRemind(hourOfDayAlarm);
                    newAppoitment.setMinuteRemind(minuteAlarm);
                    newAppoitment.setHourOfDayAppointment(hourOfDayAppointement);
                    newAppoitment.setMinuteAppointment(minuteAppointement);
                    newAppoitment.setYear(year);
                    newAppoitment.setMonth(month);
                    newAppoitment.setDayOfMonth(day);
                if(action.equals("new")||action.equals("newKnown")){
                    newAppoitment.setImage(intent.getIntExtra("image",0));
                    AppointmentFragment.Appointments.add(0, newAppoitment);
                }
                else if(action.equals("edit")){
                    cancelAlarm(AppointmentFragment.Appointments.get(position).getId());
                    AppointmentFragment.Appointments.add(position,newAppoitment);
                    AppointmentFragment.Appointments.remove(position+1);
                }
                    startAlarm(c, newAppoitment.getId());
                AppointmentFragment.mListAdapter.setData(AppointmentFragment.Appointments);
                AppointmentFragment.saveInstance();
                finish();}
                else {
                    Toast.makeText(getApplicationContext(),"Please write the name of Doctor",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate.setText(""+dayOfMonth+"/"+(month+1)+"/"+year);
        this.year = year;
        this.month = month;
        day = dayOfMonth;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (wichButton==0){
        setAlarm.setText(""+hourOfDay+":"+minute);
        hourOfDayAlarm = hourOfDay;
        minuteAlarm = minute;}
        else {
            setAppointementTime.setText(""+hourOfDay+":"+minute);
            hourOfDayAppointement = hourOfDay;
            minuteAppointement = minute;}
        }

    private void startAlarm(Calendar c, int id){
        AlarmManager alarm = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);
        intent.putExtra("name", nameDoctor.getText().toString());
        intent.putExtra("note", note.getText().toString());
        intent.putExtra("time", hourOfDayAppointement+":"+minuteAppointement);
        intent.putExtra("type", "appointment");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,id,intent,0);
        alarm.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
    private void cancelAlarm(int id){
        AlarmManager alarm = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,id,intent,0);
        alarm.cancel(pendingIntent);
    }
}
