package com.example.client1.mpcharttwo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by client1 on 7/27/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm,int noOfTabs)
    {
        super(fm);
        this.mNumOfTabs=noOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                TabFragment1 tab1=new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2=new TabFragment2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
