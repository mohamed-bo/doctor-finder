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
import com.doctor.doctorapp.classes.AppointmentReminder;
import com.doctor.doctorapp.R;
import com.doctor.doctorapp.createAppointementActivity;
import com.doctor.doctorapp.fragment.AppointmentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListAppontementAdapter extends RecyclerView.Adapter<ListAppontementAdapter.ViewHolder>  {
    List<AppointmentReminder> mDatas = new ArrayList<>();
    Context mContext;
    public ListAppontementAdapter(){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointement_case, null);
        return new ViewHolder(rootView);
    }

    public void setData(List<AppointmentReminder> data) {
        if (data != null) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<AppointmentReminder> data) {
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
        @BindView(R.id.doctor_imageappointement_case)
        ImageView doctor_image;
        @BindView(R.id.date_of_appontement)
        TextView dateOfAppointement;
        @BindView(R.id.doctor_nameappointement_case)
        TextView doctor_name;
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.deleteAppoit)
        ImageView deleteAppoit;
        @BindView(R.id.editAppoit)
        ImageView editAppoit;
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
            AppointmentReminder bean = mDatas.get(position);
            doctor_name.setText(bean.getNameOfDoctor());
            note.setText(bean.getNote());
            dateOfAppointement.setText(""+bean.getHourOfDayRemind()+":"+bean.getMinuteRemind());
            if(bean.getImage()!=0) {
                doctor_image.setImageResource(bean.getImage());
            }
            else {
                doctor_image.setImageResource(R.drawable.unknown_doctor);
            }
           deleteAppoit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cancelAlarm(bean.getId());
                    AppointmentFragment.Appointments.remove(position);
                    AppointmentFragment.saveInstance();
                    setData(AppointmentFragment.Appointments);
                }
            });
            editAppoit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, createAppointementActivity.class);
                    intent.putExtra("action","edit");
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }
            });

        }
    }
    private void cancelAlarm(int id){
        AlarmManager alarm = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,id,intent,0);
        alarm.cancel(pendingIntent);
    }
}
