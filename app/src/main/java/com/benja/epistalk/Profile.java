package com.benja.epistalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import benjamin.epistalk.R;

public class Profile extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile, container, false);
        /*String userLogin = this.getArguments().getString("ItemLogin");
        TextView login = (TextView) container.findViewById(R.id.profile_login);
        login.setText(R.string.profile_login + userLogin);*/
        return view;
    }
}