package com.doctor.doctorapp.dialogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.doctor.doctorapp.R;
import com.doctor.doctorapp.createAppointementActivity;

public class dialogDoctor extends DialogFragment {
    static int image;
    static String name;
    static String speciality;
    static String phoneNumber;
    static String address;
    static String mapAddress;
    static TextView nameView;
    static TextView specialityView;
    static TextView phoneNumberView;
    static TextView addressView;
    static ImageView imageView;
    static ImageView call;
    static ImageView bookAppointment;
    static ImageView gps;
    static Context mContext;

    public dialogDoctor(String name, String speciality, String phoneNumber, String address, int image,String mapAddress) {
        this.name = name;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.mapAddress = mapAddress;
    }

    private static final String TAG = "dialogDoctor";
static public void notifyData(){
    if(nameView!=null) {
        nameView.setText(name);
        specialityView.setText(speciality);
        phoneNumberView.setText(phoneNumber);
        addressView.setText(address);
        imageView.setImageResource(image);
        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, createAppointementActivity.class);
                intent.putExtra("action", "newKnown");
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("image",image);
                mContext.startActivity(intent);
            }
        });
    }
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.doctor_dialog,container,false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        nameView= (TextView) root.findViewById(R.id.profile_name);
        specialityView= (TextView)root.findViewById(R.id.profile_speciality);
        phoneNumberView= (TextView)root.findViewById(R.id.phone_number_profile);
        addressView = (TextView)root.findViewById(R.id.adresse_profile);
        imageView = (ImageView) root.findViewById(R.id.profile_image);
        call= (ImageView) root.findViewById(R.id.call_profile);
        bookAppointment= (ImageView) root.findViewById(R.id.book_appointment_profile);
        gps= (ImageView) root.findViewById(R.id.gps_profile);
        mContext =getActivity();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(phoneNumber);
            }
        });
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] map = mapAddress.split(", ");
                intentDirection(map[0],map[1]);
            }
        });
        notifyData();
        return root;
    }
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

  /*  double latitude = 40.714728;
    double longitude = -73.998672;
    String label = "ABC Label";
    String uriBegin = "geo:" + latitude + "," + longitude;
    String query = latitude + "," + longitude + "(" + label + ")";
    String encodedQuery = Uri.encode(query);
    String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
    Uri uri = Uri.parse(uriString);
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
    startActivity(intent);*/

  public void intentDirection(String latitude,String longitude ){
      String direction = "google.navigation:q="+latitude+","+longitude;
      Uri gmmIntentUri = Uri.parse(direction);
      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
      mapIntent.setPackage("com.google.android.apps.maps");
      startActivity(mapIntent);
}}
