/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctor.doctorapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.doctor.doctorapp.fragment.AppointmentFragment;
import com.doctor.doctorapp.fragment.DoctorsFragment;
import com.doctor.doctorapp.fragment.FavouriteFragment;
import com.doctor.doctorapp.fragment.MedicineReminderFragment;

public class viewpagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public viewpagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DoctorsFragment();
        }else if (position == 1) {
            return new FavouriteFragment();
        } else if (position == 2) {
            return new AppointmentFragment();
        } else {
            return new MedicineReminderFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "DoctorList";
        } else if (position == 1) {
            return "Favourite";
        }else if (position == 2) {
            return "Appointment Reminder";
        } else {
            return "Medicines Alarm";
        }
    }
}
