package com.benja.epistalk;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeDetails
{
    private ArrayList<User> sm;
    private TextView details;
    private ImageView arrow;

    public HomeDetails(ArrayList<User> sm, TextView details, ImageView arrow)
    {
        this.sm = sm;
        this.details = details;
        this.arrow = arrow;
    }

    public ImageView getArrow()
    {
        return arrow;
    }

    public TextView getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details.setText(details);
    }
}
