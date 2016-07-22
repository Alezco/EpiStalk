package com.benja.epistalk.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.benja.epistalk.connection.RequestManager;
import com.benja.epistalk.connection.Resfreshable;
import com.benja.epistalk.connection.User;

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

    private boolean ciscoBool;
    private boolean midBool;
    private boolean srBool;
    private boolean sm14Bool;
    private boolean otherBool;

    private HomeDetails ciscoHomeDetails;
    private HomeDetails midlabHomeDetails;
    private HomeDetails srHomeDetails;
    private HomeDetails sm14HomeDetails;
    private HomeDetails otherHomeDetails;

    private Animation animation;
    private Animation animationBack;

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

        ciscoHomeDetails = new HomeDetails((TextView) self.findViewById(R.id.cisco_details), (ImageView) self.findViewById(R.id.cisco_arrow));
        midlabHomeDetails = new HomeDetails((TextView) self.findViewById(R.id.midlab_details), (ImageView) self.findViewById(R.id.midlab_arrow));
        srHomeDetails = new HomeDetails((TextView) self.findViewById(R.id.sr_details), (ImageView) self.findViewById(R.id.sr_arrow));
        sm14HomeDetails = new HomeDetails((TextView) self.findViewById(R.id.sm14_details), (ImageView) self.findViewById(R.id.sm14_arrow));
        otherHomeDetails = new HomeDetails((TextView) self.findViewById(R.id.other_details), (ImageView) self.findViewById(R.id.other_arrow));

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_picture);
        animationBack = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_back);
        animationBack.setDuration(500);
        animation.setDuration(500);
        animation.setFillAfter(true);

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

        String ciscotext = getResources().getString(R.string.sm_cisco) + " " + ciscolen;
        String midtext = getResources().getString(R.string.sm_midlab) + " " + midlen;
        String srtext = getResources().getString(R.string.sm_labsr) + " " + srlen;
        String sm14text = getResources().getString(R.string.sm_sm14) + " " + sm14len;
        String totaltext = getResources().getString(R.string.sm_total) + " " + totallen;
        String othertext = getResources().getString(R.string.sm_other) + " " + otherlen;
        String othertotaltext = getResources().getString(R.string.sm_othertotal) + " " + othertotallen;

        cisco.setText(ciscotext);
        midlab.setText(midtext);
        sr.setText(srtext);
        sm14.setText(sm14text);
        total.setText(totaltext);
        other.setText(othertext);
        othertotal.setText(othertotaltext);

        ciscoHomeDetails.setDetails(smDetails(RequestManager.getInstance().getCisco()));
        midlabHomeDetails.setDetails(smDetails(RequestManager.getInstance().getMidlab()));
        srHomeDetails.setDetails(smDetails(RequestManager.getInstance().getSr()));
        sm14HomeDetails.setDetails(smDetails(RequestManager.getInstance().getSm14()));
        otherHomeDetails.setDetails(smDetails(RequestManager.getInstance().getOther()));

        handleDetails();
    }

    private void displayArrows()
    {
        if (RequestManager.getInstance().getCisco().size() != 0)
            ciscoHomeDetails.getArrow().setVisibility(View.VISIBLE);
        if (RequestManager.getInstance().getMidlab().size() != 0)
            midlabHomeDetails.getArrow().setVisibility(View.VISIBLE);
        if (RequestManager.getInstance().getSr().size() != 0)
            srHomeDetails.getArrow().setVisibility(View.VISIBLE);
        if (RequestManager.getInstance().getSm14().size() != 0)
            sm14HomeDetails.getArrow().setVisibility(View.VISIBLE);
        if (RequestManager.getInstance().getOther().size() != 0)
            otherHomeDetails.getArrow().setVisibility(View.VISIBLE);
    }

    private void handleDetails()
    {
        displayArrows();
        ciscoHomeDetails.getArrow().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView arrow = ciscoHomeDetails.getArrow();
                TextView details = ciscoHomeDetails.getDetails();
                if (!ciscoBool)
                {
                    details.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                    ciscoBool = true;
                }
                else
                {
                    details.setVisibility(View.GONE);
                    arrow.startAnimation(animationBack);
                    ciscoBool = false;
                }
            }
        });

        midlabHomeDetails.getArrow().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView arrow = midlabHomeDetails.getArrow();
                TextView details = midlabHomeDetails.getDetails();
                if (!midBool)
                {
                    details.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                    midBool = true;
                }
                else
                {
                    details.setVisibility(View.GONE);
                    arrow.startAnimation(animationBack);
                    midBool = false;
                }
            }
        });

        srHomeDetails.getArrow().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView arrow = srHomeDetails.getArrow();
                TextView details = srHomeDetails.getDetails();
                if (!srBool)
                {
                    details.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                    srBool = true;
                }
                else
                {
                    details.setVisibility(View.GONE);
                    arrow.startAnimation(animationBack);
                    srBool = false;
                }
            }
        });

        sm14HomeDetails.getArrow().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView arrow = sm14HomeDetails.getArrow();
                TextView details = sm14HomeDetails.getDetails();
                if (!sm14Bool)
                {
                    details.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                    sm14Bool = true;
                }
                else
                {
                    details.setVisibility(View.GONE);
                    arrow.startAnimation(animationBack);
                    sm14Bool = false;
                }
            }
        });

        otherHomeDetails.getArrow().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView arrow = otherHomeDetails.getArrow();
                TextView details = otherHomeDetails.getDetails();
                if (!otherBool)
                {
                    details.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                    otherBool = true;
                }
                else
                {
                    details.setVisibility(View.GONE);
                    arrow.startAnimation(animationBack);
                    otherBool = false;
                }
            }
        });
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
        Map<String, Integer> newMap = new TreeMap<>(map);
        String res = "";
        if (!newMap.isEmpty())
            res = System.getProperty("line.separator");
        for (Map.Entry<String, Integer> e : newMap.entrySet())
            res += e.getValue() + " " + e.getKey() + System.getProperty("line.separator");

        return res;
    }
}