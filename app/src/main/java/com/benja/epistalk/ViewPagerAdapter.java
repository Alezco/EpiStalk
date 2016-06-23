package com.benja.epistalk;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import benjamin.epistalk.R;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    int ciscolen = 0;
    int midlablen = 0;
    int srlen = 0;
    int sm14len = 0;
    int otherlen = 0;
    Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
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
    public CharSequence getPageTitle(int position)
    {
        String s = "";
        switch (position)
        {
            case 0:
                s = " Home";
                break;
            case 1:
                s = " Cisco(" + ciscolen + ")";
                break;
            case 2:
                s = " Mid-Lab(" + midlablen + ")";
                break;
            case 3:
                s = " Lab-SR(" + srlen + ")";
                break;
            case 4:
                s = " SM-14(" + sm14len + ")";
                break;
            case 5:
                s = " Other(" + otherlen + ")";
                break;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder(s);
        Drawable drawable;
        if (position == 0)
            drawable = context.getResources().getDrawable(R.mipmap.home_icon);
        else
            drawable = context.getResources().getDrawable(R.mipmap.desktop_icon_white);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2 - 10, drawable.getIntrinsicHeight() / 2 - 10);
        ImageSpan imageSpan = new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
}
