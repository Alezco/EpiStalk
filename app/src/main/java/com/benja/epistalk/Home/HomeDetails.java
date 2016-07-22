package com.benja.epistalk.home;

import android.widget.ImageView;
import android.widget.TextView;

public class HomeDetails
{
    private TextView details;
    private ImageView arrow;

    public HomeDetails(TextView details, ImageView arrow)
    {
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
