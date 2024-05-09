package com.doctor.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.doctor.doctorapp.adapter.viewpagerAdapter;
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigation;
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem;
import com.tenclouds.fluidbottomnavigation.listener.OnTabSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FluidBottomNavigation fluidBottomNavigation;
        List fluidBottomNavigationItems=new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewPager) ;
        viewpagerAdapter adapter = new viewpagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        fluidBottomNavigation = findViewById(R.id.fluidBottomNavigation);
        fluidBottomNavigationItems.add(new FluidBottomNavigationItem(
                getString(R.string.Home), ContextCompat.getDrawable(this, R.drawable.bar_doctor)));
        fluidBottomNavigationItems.add(new FluidBottomNavigationItem(
                getString(R.string.Favourite), ContextCompat.getDrawable(this, R.drawable.bar_favourite)));
        fluidBottomNavigationItems.add(new FluidBottomNavigationItem(
                getString(R.string.Appointment), ContextCompat.getDrawable(this, R.drawable.bar_appontment)));
        fluidBottomNavigationItems.add(new FluidBottomNavigationItem(
                getString(R.string.Medicine),
                ContextCompat.getDrawable(this, R.drawable.bar_medicine)));
        fluidBottomNavigation.setItems(fluidBottomNavigationItems);
        fluidBottomNavigation.setOnTabSelectedListener(new OnTabSelectedListener() {
                                                           @Override
                                                           public void onTabSelected(int i) {
                                                               viewPager.setCurrentItem(i);
                                                           }
                                                       }
        );
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
fluidBottomNavigation.selectTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
    }


}
