package com.example.benja.epistalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int ciscolen = 0;
    int midlablen = 0;
    int srlen = 0;
    int sm14len = 0;
    int otherlen = 0;

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
        ciscolen = RequestManager.getInstance().getCisco().size();
        midlablen = RequestManager.getInstance().getMidlab().size();
        srlen = RequestManager.getInstance().getSr().size();
        sm14len = RequestManager.getInstance().getSm14().size();
        otherlen = RequestManager.getInstance().getOther().size();
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
                return "Cisco(" + ciscolen + ")";
            case 2:
                return "Mid-Lab(" + midlablen + ")";
            case 3:
                return "Lab-SR(" + srlen + ")";
            case 4:
                return "SM-14(" + sm14len + ")";
            case 5:
                return "Other(" + otherlen + ")";
        }
        return "Test";
    }
}
