package com.doctor.doctorapp.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.doctor.doctorapp.alarmnotification.AlarmReciever;
import com.doctor.doctorapp.R;
import com.doctor.doctorapp.classes.MedicinesReminder;
import com.doctor.doctorapp.classes.time;
import com.doctor.doctorapp.createMedicineReminder;
import com.doctor.doctorapp.fragment.MedicineReminderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListMedicinesAdapter extends RecyclerView.Adapter<ListMedicinesAdapter.ViewHolder>  {
    List<MedicinesReminder> mDatas = new ArrayList<>();
    Context mContext;
    public ListMedicinesAdapter(){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_reminder_case, null);
        return new ViewHolder(rootView);
    }

    public void setData(List<MedicinesReminder> data) {
        if (data != null) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<MedicinesReminder> data) {
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
        @BindView(R.id.Medicine_name_case)
        TextView medicine_name;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.deleteMedicine)
        ImageView deleteMedicine;
        @BindView(R.id.editMedicine)
        ImageView editMedicine;
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
            MedicinesReminder bean = mDatas.get(position);
            medicine_name.setText(bean.getNameOfMedicine());
            quantity.setText(bean.getQuantityOfMedicine());
         //   dateOfMedicine.setText(""+bean.getHourOfDayRemind()+":"+bean.getMinuteRemind());
            mRootView.setTag(position);
            deleteMedicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   cancelAlarms(bean.times);
                  MedicineReminderFragment.Medicines.remove(position);
                  MedicineReminderFragment.saveInstance();
                   setData(MedicineReminderFragment.Medicines);
                }
            });
            editMedicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, createMedicineReminder.class);
                    intent.putExtra("action","edit");
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }
            });

        }
    }
    private void cancelAlarms(ArrayList<time> times){
        for(int i=0;i<times.size();i++) {
            AlarmManager alarm = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mContext, AlarmReciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, times.get(i).getId(), intent, 0);
            alarm.cancel(pendingIntent);
        }
    }
}
