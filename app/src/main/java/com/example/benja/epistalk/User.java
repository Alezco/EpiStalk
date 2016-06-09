package com.example.benja.epistalk;

public class User
{
    private String login;
    private String ip;
    private String promo;

    public User(String login, String ip, String promo)
    {
        this.login = login;
        this.ip = ip;
        this.promo = promo;
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
}
