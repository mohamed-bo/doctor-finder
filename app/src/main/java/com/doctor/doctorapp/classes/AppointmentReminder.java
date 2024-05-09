package com.doctor.doctorapp.classes;

import android.app.PendingIntent;

import java.util.Date;

public class AppointmentReminder {
    private int id ;
    private String nameOfDoctor;
    private String adresse;
    private String note;
    private PendingIntent pendingIntent;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hourOfDayAppointment;
    private int minuteAppointment;
    private int hourOfDayRemind;
    private int minuteRemind;
    private int image;
    public AppointmentReminder(){

        id = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);;
    }
    public AppointmentReminder(String nameOfDoctor, String adresse){
        this.nameOfDoctor = nameOfDoctor;
        this.adresse = adresse;
    }
    public String getNameOfDoctor() {
        return nameOfDoctor;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNote() {
        return note;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getHourOfDayAppointment() {
        return hourOfDayAppointment;
    }

    public int getMinuteAppointment() {
        return minuteAppointment;
    }

    public int getHourOfDayRemind() {
        return hourOfDayRemind;
    }

    public int getMinuteRemind() {
        return minuteRemind;
    }

    public void setNameOfDoctor(String nameOfDoctor) {
        this.nameOfDoctor = nameOfDoctor;
    }

    public void setAdresse(String adress) {
        this.adresse = adress;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setHourOfDayAppointment(int hourOfDayAppointment) {
        this.hourOfDayAppointment = hourOfDayAppointment;
    }

    public void setMinuteAppointment(int minuteAppointment) {
        this.minuteAppointment = minuteAppointment;
    }

    public void setHourOfDayRemind(int hourOfDayRemind) {
        this.hourOfDayRemind = hourOfDayRemind;
    }

    public int getId() {
        return id;
    }

    public void setMinuteRemind(int minuteRemind) {
        this.minuteRemind = minuteRemind;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
