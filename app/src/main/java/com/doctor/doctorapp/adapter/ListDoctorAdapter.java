package com.doctor.doctorapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.doctor.doctorapp.classes.Doctor;
import com.doctor.doctorapp.R;
import com.doctor.doctorapp.dialogs.dialogDoctor;
import com.doctor.doctorapp.fragment.DoctorsFragment;
import com.doctor.doctorapp.fragment.FavouriteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListDoctorAdapter extends RecyclerView.Adapter<ListDoctorAdapter.ViewHolder>  {
    int index;
    List<Doctor> mDatas = new ArrayList<>();
    Context mContext;
    Boolean favouriteFragment;
    public ListDoctorAdapter(Boolean favouriteFragment){
        this.favouriteFragment = favouriteFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_case, null);
        return new ViewHolder(rootView);
    }

    public void setData(List<Doctor> data) {
        if (data != null) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<Doctor> data) {
        if (data != null) {
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setUI(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.doctor_image_case)
        ImageView doctor_image;
        @BindView(R.id.doctor_speciality_case)
        TextView doctor_speciality;
        @BindView(R.id.doctor_name_case)
        TextView doctor_name;
        @BindView(R.id.check_favorite)
        CheckBox check_favorite;

@BindView(R.id.cardViewDoctor)
        View cardViewDoctor;

        View mRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mRootView = itemView;
            mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        public void setUI(int position) {
            Doctor bean = mDatas.get(position);
            doctor_image.setImageResource(bean.getImage());
            doctor_speciality.setText(bean.getSpeciality());
            doctor_name.setText(bean.getName());
            cardViewDoctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDoctor doctorDialog = new dialogDoctor(bean.getName(),bean.getSpeciality(),bean.getPhone_number(),bean.getAdress(),bean.getImage(),bean.getMap_adress());

                    doctorDialog.show(((AppCompatActivity)mContext).getSupportFragmentManager(),"doctor dialog" );
                        doctorDialog.notifyData();

                }
            });
            check_favorite.setOnCheckedChangeListener(null);
            if(bean.isFavorite()){
                check_favorite.setChecked(true);
            }
            else {
                check_favorite.setChecked(false);
            }
            check_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = DoctorsFragment.Doctors.indexOf(bean);
                    if(index!=-1){
                        if (check_favorite.isChecked()) {
                            Toast.makeText(mContext,"The Doctor has been added to your favourites List",Toast.LENGTH_SHORT).show();
                            DoctorsFragment.Doctors.get(index).setFavorite(true);
                            if(favouriteFragment){
                                DoctorsFragment.notifyDataChanged();
                            }
                            DoctorsFragment.saveInstance();
                            FavouriteFragment.notifyDataAdd(bean);
                        } else {
                            Toast.makeText(mContext,"The Doctor has been removed from your favourites List", Toast.LENGTH_SHORT).show();
                            DoctorsFragment.Doctors.get(index).setFavorite(false);
                            if(favouriteFragment){
                                DoctorsFragment.notifyDataChanged();
                            }
                            DoctorsFragment.saveInstance();
                            FavouriteFragment.notifyDataRemove(bean);
                        }
                    }
                }
            });
            check_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 }
            });
        }
    }

}
