package com.doctor.doctorapp.classes;

import java.util.ArrayList;

public class MedicinesReminder {
    private String nameOfMedicine;
    private String quantityOfMedicine;
    private String note;
    public ArrayList<time> times = new ArrayList<>();

    public MedicinesReminder(String nameOfMedicine, String quantityOfMedicine, String note) {
        this.nameOfMedicine = nameOfMedicine;
        this.quantityOfMedicine = quantityOfMedicine;
        this.note = note;
    }
    public MedicinesReminder(){

    }

    public void setTimes(ArrayList<time> times) {
        this.times = times;
    }

    public void setNameOfMedicine(String nameOfMedicine) {
        this.nameOfMedicine = nameOfMedicine;
    }

    public void setQuantityOfMedicine(String quantityOfMedicine) {
        this.quantityOfMedicine = quantityOfMedicine;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNameOfMedicine() {
        return nameOfMedicine;
    }

    public String getQuantityOfMedicine() {
        return quantityOfMedicine;
    }

    public String getNote() {
        return note;
    }
}




