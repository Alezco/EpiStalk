package com.benja.epistalk;

import android.content.Context;

import benjamin.epistalk.R;

public class User
{
    private String socket;
    private String login;
    private String ip;
    private String timeConnection;
    private String lastStatusChange;
    private String inPIE;
    private String location;
    private String promo;
    private String status;
    private String userData;

    public User(String socket, String login, String ip, String timeConnection, String lastStatusChange, String inPIE, String location, String promo, String status, String userData)
    {
        this.socket = socket;
        this.login = login;
        this.ip = ip;
        this.timeConnection = timeConnection;
        this.lastStatusChange = lastStatusChange;
        this.inPIE = inPIE;
        this.location = location;
        this.promo = promo;
        this.status = status;
        this.userData = userData;
    }

    public String getUserData()
    {
        return userData;
    }

    public String getSocket()
    {
        return socket;
    }

    public String getTimeConnection()
    {
        return timeConnection;
    }

    public String getLastStatusChange()
    {
        return lastStatusChange;
    }

    public String getInPIE()
    {
        return inPIE;
    }

    public String getStatus()
    {
        return status;
    }

    public String getLocation()
    {
        return location;
    }

    public String getLogin()
    {
        return login;
    }

    public String getIp()
    {
        return ip;
    }

    public String getPromo()
    {
        return promo;
    }

    public String getSm(Context context)
    {
        if (ip.startsWith("10.224.32."))
            return context.getResources().getString(R.string.sm_cisco);
        else if (ip.startsWith("10.224.33."))
            return context.getResources().getString(R.string.sm_midlab);
        else if (ip.startsWith("10.224.34."))
            return context.getResources().getString(R.string.sm_labsr);
        else if (ip.startsWith("10.224.35."))
            return context.getResources().getString(R.string.sm_sm14);
        else
            return context.getResources().getString(R.string.sm_other);
    }
}