package com.benja.epistalk;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import benjamin.epistalk.R;

public class Home extends Fragment implements Resfreshable
{
    private TextView cisco;
    private TextView midlab;
    private TextView sr;
    private TextView sm14;
    private TextView total;
    private TextView other;
    private TextView othertotal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View self = inflater.inflate(R.layout.home, container, false);
        cisco = (TextView) self.findViewById(R.id.textView2);
        midlab = (TextView) self.findViewById(R.id.textView3);
        sr = (TextView) self.findViewById(R.id.textView4);
        sm14 = (TextView) self.findViewById(R.id.textView5);
        total = (TextView) self.findViewById(R.id.textView7);
        other = (TextView) self.findViewById(R.id.textView6);
        othertotal = (TextView) self.findViewById(R.id.textView8);

        refresh();
        handleCards(self);

        return self;
    }

    public void handleCards(View self)
    {
        CardView ciscoCardview = (CardView) self.findViewById(R.id.cisco_cardview);
        ciscoCardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(1);
            }
        });

        CardView midlabCardview = (CardView) self.findViewById(R.id.midlab_cardview);
        midlabCardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });

        CardView srCardview = (CardView) self.findViewById(R.id.sr_cardview);
        srCardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(3);
            }
        });

        CardView sm14Cardview = (CardView) self.findViewById(R.id.sm14_cardview);
        sm14Cardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(4);
            }
        });

        CardView otherCardview = (CardView) self.findViewById(R.id.other_cardview);
        otherCardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(5);
            }
        });
    }

    @Override
    public void refresh()
    {
        int ciscolen = RequestManager.getInstance().getCisco().size();
        int midlen = RequestManager.getInstance().getMidlab().size();
        int srlen = RequestManager.getInstance().getSr().size();
        int sm14len = RequestManager.getInstance().getSm14().size();
        int otherlen = RequestManager.getInstance().getOther().size();
        int totallen = ciscolen + midlen + srlen + sm14len;
        int othertotallen = totallen + otherlen;

        String ciscotext = getResources().getString(R.string.sm_cisco) + " " + ciscolen + smDetails(RequestManager.getInstance().getCisco());
        String midtext = getResources().getString(R.string.sm_midlab) + " " + midlen + smDetails(RequestManager.getInstance().getMidlab());
        String srtext = getResources().getString(R.string.sm_labsr) + " " + srlen + smDetails(RequestManager.getInstance().getSr());
        String sm14text = getResources().getString(R.string.sm_sm14) + " " + sm14len + smDetails(RequestManager.getInstance().getSm14());
        String totaltext = getResources().getString(R.string.sm_total) + " " + totallen;
        String othertext = getResources().getString(R.string.sm_other) + " " + otherlen + smDetails(RequestManager.getInstance().getOther());
        String othertotaltext = getResources().getString(R.string.sm_othertotal) + " " + othertotallen;

        cisco.setText(ciscotext);
        midlab.setText(midtext);
        sr.setText(srtext);
        sm14.setText(sm14text);
        total.setText(totaltext);
        other.setText(othertext);
        othertotal.setText(othertotaltext);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        RequestManager.getInstance().unregisterRefreshable(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        RequestManager.getInstance().registerRefreshable(this);
        this.refresh();
    }

    private String smDetails(ArrayList<User> users)
    {
        HashMap<String, Integer> map = new HashMap<>();
        for (User u : users)
        {
            if (!map.containsKey(u.getPromo()))
                map.put(u.getPromo(), 1);
            else
                map.put(u.getPromo(), map.get(u.getPromo()) + 1);
        }
        Map<String, Integer> newMap = new TreeMap<String, Integer>(map);
        String res = "";
        if (!newMap.isEmpty())
            res = System.getProperty("line.separator") + System.getProperty("line.separator");
        for (Map.Entry<String, Integer> e : newMap.entrySet())
            res += e.getValue() + " " + e.getKey() + System.getProperty("line.separator");

        return res;
    }
}