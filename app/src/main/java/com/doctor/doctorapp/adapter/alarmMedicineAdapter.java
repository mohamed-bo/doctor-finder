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
import com.doctor.doctorapp.classes.time;
import com.doctor.doctorapp.createMedicineReminder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class alarmMedicineAdapter extends RecyclerView.Adapter<alarmMedicineAdapter.ViewHolder>  {
    List<time> mDatas = new ArrayList<>();
    Context mContext;
    public alarmMedicineAdapter(){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_case, null);
        return new ViewHolder(rootView);
    }

    public void setData(List<time> data) {
        if (data != null) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<time> data) {
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
        @BindView(R.id.date_of_list)
        TextView date_of_list;
        @BindView(R.id.deleteDate)
        ImageView deleteDate;
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
            time bean = mDatas.get(position);
            date_of_list.setText(bean.getHour()+":"+bean.getMinute());
            deleteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cancelAlarm(bean.getId());
                    createMedicineReminder.alarms.remove(position);
                    setData(createMedicineReminder.alarms);
                }
            });
       /*     card_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("MY_A", "MyClass.getView() — get item number  cccccccccccccccccc ");
                    po=mDatas.get(position).getId();

                    if (!sSave.get(po).equals("++")){
                        folioReader.setReadLocator(ReadLocator.fromJson(sSave.get(po)));
                    }
                    folioReader.openBook(mDatas.get(position).getRes());
                }
            });
            checkbox.setOnCheckedChangeListener(null);
            if(categoryy.fav.get(mDatas.get(position).getId())==1){
                checkbox.setChecked(true);
            }
            if(categoryy.fav.get(mDatas.get(position).getId())==2){
                checkbox.setChecked(false);
            }

            if(mDatas.get(position).getCheak()==2){
                checkbox.setVisibility(View.GONE);
            }
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(mContext,"تمت إضافة الكتاب إلى المفضلة",Toast.LENGTH_SHORT).show();
                        categoryy.fav.set(mDatas.get(position).getId(),1);
                        categoryy.setDefaults();
                    } else {
                        Toast.makeText(mContext,"تمت إزالة الكتاب من المفضلة",Toast.LENGTH_SHORT).show();
                        categoryy.fav.set(mDatas.get(position).getId(),2);
                        categoryy.setDefaults();
                    }
                }
            });*/
        }
    }
    private void cancelAlarm(int id){
        AlarmManager alarm = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,id,intent,0);
        alarm.cancel(pendingIntent);
    }
}
