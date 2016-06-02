package com.example.benja.epistalk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int ciscolen = 0;

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pagenum", position);
        tabFragment.setArguments(bundle);
        //ciscolen = tabFragment.getCiscoLen();
        if (position == 0)
            return new Home();
        return tabFragment;
    }

    @Override
    public int getCount()
    {
        return 6;
    }

    @Override
    public String getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Home";
            case 1:
                return "Cisco";
            case 2:
                return "Mid-Lab";
            case 3:
                return "Lab-SR";
            case 4:
                return "SM-14";
            case 5:
                return "Other";
        }
        return "Test";
    }
}
