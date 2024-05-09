package com.doctor.doctorapp.classes;

import java.util.Date;

public class time{
   private int hour;
    private int minute;
    private int id;

    public time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        id = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getId() {
        return id;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinujte(int minute) {
        this.minute = minute;
    }

    public void setId(int id) {
        this.id = id;
    }
}
