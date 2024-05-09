package com.doctor.doctorapp.classes;

public class Doctor {
    private String name =" name of Doctor";
    private int id;
    private String phone_number;
    private String adress;
    private String map_adress;
    private boolean favorite;
    private String speciality= "name of special";
    private int image;
    public Doctor (String name ,int id ,String phone_number ,String adress, String map_adress, String speciality, int image){
        this.name=name;
        this.id=id;
        this.phone_number=phone_number;
        this.adress=adress;
        this.map_adress=map_adress;
        this.speciality=speciality;
        this.image= image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getMap_adress() {
        return map_adress;
    }

    public int getImage() {
        return image;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
