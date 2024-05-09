package com.doctor.doctorapp.alarmnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import java.util.Date;

public class AlarmReciever extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        String name = intent.getStringExtra("name");
        String note = intent.getStringExtra("note");
        String time = intent.getStringExtra("time");
        String type = intent.getStringExtra("type");
        NotificationHelper mNotification = new NotificationHelper(context);
        NotificationCompat.Builder nb;
        if(type.equals("appointment")) {
            nb = mNotification.getChanellNotification("Appointment Reminder","You have an Appointment With: " + name + "\nAt:" + time+ "\nnote:"+note);
            mNotification.getManager().notify(m, nb.build());
        }
        else{
            String quantitiy = intent.getStringExtra("quantitiy");
            nb = mNotification.getChanellNotification("Time for medicine "   ,name + "\nquantity:"+quantitiy +"\nnote:"+ note);
            mNotification.getManager().notify(m, nb.build());
        }

    }
}
