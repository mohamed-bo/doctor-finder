package com.doctor.doctorapp.alarmnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

import com.doctor.doctorapp.R;


public class NotificationHelper extends ContextWrapper {
    NotificationManager manager;
    public NotificationHelper(Context base) {
        super(base);

        creatChannel();}
    public void creatChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("chanel1","chanel1id", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(R.color.colorPrimary);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(channel);
        }
    }
    public NotificationManager getManager(){
        if(manager==null){

            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
    public NotificationCompat.Builder getChanellNotification(String title , String description ){
        return new NotificationCompat.Builder(getApplicationContext(),"chanel1")
                .setContentTitle(title)
                .setContentText(description)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(description))
                .setSmallIcon(R.drawable.color_alarm);

    }
}
