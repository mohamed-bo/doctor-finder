package com.doctor.doctorapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doctor.doctorapp.alarmnotification.AlarmReciever;
import com.doctor.doctorapp.alarmnotification.NotificationHelper;
import com.doctor.doctorapp.dialogs.DatePickerFragment;
import com.doctor.doctorapp.dialogs.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;


public class PlusOneFragment extends Fragment  implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener{

    public PlusOneFragment() {
        // Required empty public constructor
    }

    TextView textView;
    private NotificationHelper mNotification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mNotification = new NotificationHelper(getActivity());
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        textView = (TextView)  view.findViewById(R.id.text_time);
        Button button = (Button)  view.findViewById(R.id.button);
        Button buttonii = (Button)  view.findViewById(R.id.buttonii);
        DialogFragment newFragment = new DatePickerFragment(this);
        DialogFragment time = new TimePickerFragment(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newFragment.show(getChildFragmentManager(), "timePicker");
                cancelAlarm();
            }
        });
        buttonii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time.show(getChildFragmentManager(), "timePicker");

            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        textView.setText("hours " +   year+ "  minute  " + month+ "  day  " + dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.v("maingeg","aaaaaaasffegewgfdg");
        textView.setText("hours " + hourOfDay + "minute" + minute);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND,0);
        startAlarm(c);
      /*  NotificationCompat.Builder nb = mNotification.getChanellNotification("aaaa","bbbb");
        mNotification.getManager().notify(1,nb.build());*/
    }
    public void updateTimeTEXT(Calendar c){
        String timeText = "alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

    }
    private void startAlarm(Calendar c){
        AlarmManager alarm = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),1,intent,0);
        alarm.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
    private void cancelAlarm(){
        AlarmManager alarm = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),1,intent,0);
        alarm.cancel(pendingIntent);
    }

}
