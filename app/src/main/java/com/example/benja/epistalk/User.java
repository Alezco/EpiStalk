package com.example.benja.epistalk;

/**
 * Created by benja on 05/05/2016.
 */
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

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPromo()
    {
        return promo;
    }

    public void setPromo(String promo)
    {
        this.promo = promo;
    }
}
