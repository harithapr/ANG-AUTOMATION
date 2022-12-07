package com.example.details;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.panchayathreports;
import com.example.supervisorsreports;
public class MyAdapter extends PagerAdapter {
    private cdpohomepagereports mycdpo;
    int tabCount;

    public MyAdapter(cdpohomepagereports cdpohomepagereports, FragmentManager supportFragmentManager, int tabCount) {
       //super(supportFragmentManager);
        mycdpo = cdpohomepagereports;
        this.tabCount = tabCount;
    }




    @NonNull
   // @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                panchayathreports panchayathreports=new panchayathreports();
                return panchayathreports;
            case 1:
                supervisorsreports supervisorsreports=new supervisorsreports();
                return supervisorsreports;


        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
