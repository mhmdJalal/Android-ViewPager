package com.codepolitan.viewpager2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.codepolitan.viewpager2.fragment.TutorialFragment;
import com.codepolitan.viewpager2.fragment.InfoFragment;
import com.codepolitan.viewpager2.fragment.NewsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    // Constructor
    public ViewPagerAdapter(FragmentManager fm) {
        // memanggil constructor superclass
        super(fm);
    }

    // Membuat objek fragment untuk ditampilkan pada viewpager
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new NewsFragment();
            case 1: return new InfoFragment();
            case 2: return new TutorialFragment();
            default: return new NewsFragment();
        }
    }

    // Jumlah Fragment yang akan di tampilkan pada viewPager
    @Override
    public int getCount() {
        return 3;
    }
}
