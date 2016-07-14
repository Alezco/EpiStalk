package com.benja.epistalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class RequestManager
{
    private static RequestManager ourInstance = new RequestManager();
    private ArrayList<User> cisco;
    private ArrayList<User> midlab;
    private ArrayList<User> sr;
    private ArrayList<User> sm14;
    private ArrayList<User> other;
    private ArrayList<User> mainList;
    private ArrayList<Resfreshable> fragments;

    private RequestManager()
    {
        cisco = new ArrayList<>();
        midlab = new ArrayList<>();
        sr = new ArrayList<>();
        sm14 = new ArrayList<>();
        other = new ArrayList<>();
        fragments = new ArrayList<>();
        mainList = new ArrayList<>();
        new ThreadConnect().execute();
    }

    public static RequestManager getInstance()
    {
        return ourInstance;
    }

    public ArrayList<User> getCisco()
    {
        return cisco;
    }

    public ArrayList<User> getMidlab()
    {
        return midlab;
    }

    public ArrayList<User> getSr()
    {
        return sr;
    }

    public ArrayList<User> getSm14()
    {
        return sm14;
    }

    public ArrayList<User> getOther()
    {
        return other;
    }

    public ArrayList<User> getMainList()
    {
        return mainList;
    }

    public void refresh()
    {
        new ThreadConnect().execute();
    }

    public void doRefresh()
    {
        for (Resfreshable r : fragments)
            r.refresh();
    }

    public void connectServer()
    {
        clearLists();
        HashSet<User> ciscoH = new HashSet<>();
        HashSet<User> midH = new HashSet<>();
        HashSet<User> srH = new HashSet<>();
        HashSet<User> sm14H = new HashSet<>();
        HashSet<User> otherH = new HashSet<>();
        try
        {
            Socket socket = new Socket("ns-server.epita.fr", 4242);
            socket.setReceiveBufferSize(8192);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = in.read(buffer, 0, 8192);
            if (bytesRead == -1)
               return;
            out.write("list_users\n".getBytes());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            while (true)
            {
                String line = bufferedReader.readLine();
                if (line.contains("rep 002") || line.equals(""))
                    break;
                String[] split = line.split(" ");
                User user = new User(split[0], split[1], split[2], split[3], split[4], split[5],
                                     split[8], split[9], split[10], split[11]);
                mainList.add(user);
                fillLists(user, ciscoH, midH, srH, sm14H, otherH);
            }
            bufferedReader.close();
            removeDuplicates(ciscoH, midH, srH, sm14H, otherH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void clearLists()
    {
        cisco.clear();
        midlab.clear();
        sr.clear();
        sm14.clear();
        other.clear();
    }

    private void fillLists(User user, HashSet<User> ciscoH, HashSet<User> midH,
                           HashSet<User> srH, HashSet<User> sm14H, HashSet<User> otherH)
    {
        if (user.getIp().startsWith("10.224.32."))
            ciscoH.add(user);
        else if (user.getIp().startsWith("10.224.33."))
            midH.add(user);
        else if (user.getIp().startsWith("10.224.34."))
            srH.add(user);
        else if (user.getIp().startsWith("10.224.35."))
            sm14H.add(user);
        else
            otherH.add(user);
    }

    private void removeDuplicates(HashSet<User> ciscoH, HashSet<User> midH,
                                  HashSet<User> srH, HashSet<User> sm14H, HashSet<User> otherH)
    {
        cisco.addAll(ciscoH);
        midlab.addAll(midH);
        sr.addAll(srH);
        sm14.addAll(sm14H);
        other.addAll(otherH);
    }

    public void registerRefreshable(Resfreshable resfreshable)
    {
        if (!fragments.contains(resfreshable))
            fragments.add(resfreshable);
    }

    public void unregisterRefreshable(Resfreshable resfreshable)
    {
        if (fragments.contains(resfreshable))
            fragments.remove(resfreshable);
    }
}