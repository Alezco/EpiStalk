package com.example.benja.epistalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class TabFragment extends Fragment {
    private ArrayList<String> cisco;
    private ArrayList<String> midlab;
    private ArrayList<String> sr;
    private ArrayList<String> sm14;
    private ArrayList<String> other;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tabs, container, false);
        ListView list_sm = (ListView) rootView.findViewById(R.id.list_sm);
        ArrayList<String> arrayList = new ArrayList<>();
        cisco = new ArrayList<>();
        midlab = new ArrayList<>();
        sr = new ArrayList<>();
        sm14 = new ArrayList<>();
        other = new ArrayList<>();

        int pageNum = this.getArguments().getInt("pagenum");
        if (pageNum == 1)
            arrayList = cisco;
        if (pageNum == 2)
            arrayList = midlab;
        if (pageNum == 3)
            arrayList = sr;
        if (pageNum == 4)
            arrayList = sm14;
        if (pageNum == 5)
            arrayList = other;
        connectServer();
        HashSet hashSet = new HashSet();
        hashSet.addAll(arrayList);
        arrayList.clear();
        arrayList.addAll(hashSet);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);
        assert list_sm != null;
        list_sm.setAdapter(arrayAdapter);

        return rootView;
    }
    public void connectServer()
    {
        try
        {
            Socket socket = new Socket("ns-server.epita.fr", 4242);
            socket.setReceiveBufferSize(8192);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            byte[] buffer = new byte[8192];
            in.read(buffer, 0, 8192);
            out.write("list_users\n".getBytes());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            while (true)
            {
                String line = bufferedReader.readLine();
                if (line.contains("rep 002") || line.equals(""))
                    break;
                String[] split = line.split(" ");
                User user = new User(split[1], split[2], split[9]);
                String userString= String.format("%-15s%-20s%-20s", user.getLogin(), user.getIp(), user.getPromo());
                if (user.getIp().startsWith("10.224.32."))
                    cisco.add(userString);
                else if (user.getIp().startsWith("10.224.33."))
                    midlab.add(userString);
                else if (user.getIp().startsWith("10.224.34."))
                    sr.add(userString);
                else if (user.getIp().startsWith("10.224.35."))
                    sm14.add(userString);
                else
                    other.add(userString);

            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    int getCiscoLen()
    {
        return cisco.size();
    }
}
