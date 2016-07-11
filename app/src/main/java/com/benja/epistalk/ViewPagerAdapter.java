package com.benja.epistalk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import benjamin.epistalk.R;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private int ciscolen;
    private int midlablen;
    private int srlen;
    private int sm14len;
    private int otherlen;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        ciscolen = 0;
        midlablen = 0;
        srlen = 0;
        sm14len = 0;
        otherlen = 0;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNum", position);
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
        String s = " ";
        switch (position)
        {
            case 0:
                s += context.getResources().getString(R.string.vp_home);
                break;
            case 1:
                s += context.getResources().getString(R.string.vp_cisco) + ciscolen + context.getResources().getString(R.string.vp_close);
                break;
            case 2:
                s += context.getResources().getString(R.string.vp_midlab) + midlablen + context.getResources().getString(R.string.vp_close);
                break;
            case 3:
                s += context.getResources().getString(R.string.vp_sr) + srlen + context.getResources().getString(R.string.vp_close);
                break;
            case 4:
                s += context.getResources().getString(R.string.vp_sm14) + sm14len + context.getResources().getString(R.string.vp_close);
                break;
            case 5:
                s += context.getResources().getString(R.string.vp_other) + otherlen + context.getResources().getString(R.string.vp_close);
                break;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder(s);
        Drawable drawable;
        if (position == 0)
            drawable = ResourcesCompat.getDrawable(context.getResources(), R.mipmap.home_icon, null);
        else
            drawable = ResourcesCompat.getDrawable(context.getResources(), R.mipmap.desktop_icon_white, null);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2 - 10, drawable.getIntrinsicHeight() / 2 - 10);
        ImageSpan imageSpan = new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
}