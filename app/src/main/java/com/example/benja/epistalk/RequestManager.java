package com.example.benja.epistalk;

public class RequestManager
{
    private static RequestManager ourInstance = new RequestManager();

    public static RequestManager getInstance()
    {
        return ourInstance;
    }

    private RequestManager()
    {
    }
}
