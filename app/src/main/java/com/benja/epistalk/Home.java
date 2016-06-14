package com.benja.epistalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import benjamin.epistalk.R;

public class Home extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View self = inflater.inflate(R.layout.home, container, false);
        TextView cisco = (TextView) self.findViewById(R.id.textView2);
        TextView midlab = (TextView) self.findViewById(R.id.textView3);
        TextView sr = (TextView) self.findViewById(R.id.textView4);
        TextView sm14 = (TextView) self.findViewById(R.id.textView5);
        TextView total = (TextView) self.findViewById(R.id.textView7);
        TextView other = (TextView) self.findViewById(R.id.textView6);
        TextView othertotal = (TextView) self.findViewById(R.id.textView8);

        int ciscolen = RequestManager.getInstance().getCisco().size();
        int midlen = RequestManager.getInstance().getMidlab().size();
        int srlen = RequestManager.getInstance().getSr().size();
        int sm14len = RequestManager.getInstance().getSm14().size();
        int otherlen = RequestManager.getInstance().getOther().size();
        int totallen = ciscolen + midlen + srlen + sm14len;
        int othertotallen = totallen + otherlen;

        String ciscotext = "Cisco : " + ciscolen;
        String midtext = "Mid-lab : " + midlen;
        String srtext = "Lab-SR : " + srlen;
        String sm14text = "SM-14 : " + sm14len;
        String totaltext = "Total : " + totallen;
        String othertext = "Other : " + otherlen;
        String othertotaltext = "Other Total : " + othertotallen;

        cisco.setText(ciscotext);
        midlab.setText(midtext);
        sr.setText(srtext);
        sm14.setText(sm14text);
        total.setText(totaltext);
        other.setText(othertext);
        othertotal.setText(othertotaltext);

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
}
