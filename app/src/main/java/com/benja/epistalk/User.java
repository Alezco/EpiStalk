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

    public String getUserDataAsString(Context context)
    {
        if (userData.equals("none"))
            return context.getResources().getString(R.string.unknown);
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

    public String getInPIEAsString(Context context)
    {
        if (inPIE.equals("3"))
            return context.getResources().getString(R.string.yes);
        else
            return context.getResources().getString(R.string.no);
    }

    public String getStatus()
    {
        return status;
    }

    public String getLocationAsString(Context context)
    {
        if (location.equals("none"))
            return context.getResources().getString(R.string.unknown);
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
            return context.getResources().getString(R.string.profile_cisco);
        else if (ip.startsWith("10.224.33."))
            return context.getResources().getString(R.string.profile_midlab);
        else if (ip.startsWith("10.224.34."))
            return context.getResources().getString(R.string.profile_sr);
        else if (ip.startsWith("10.224.35."))
            return context.getResources().getString(R.string.profile_sm14);
        else
            return context.getResources().getString(R.string.profile_other);
    }
}