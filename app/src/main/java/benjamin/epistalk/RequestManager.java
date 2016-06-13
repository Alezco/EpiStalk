package benjamin.epistalk;

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
    private ArrayList<String> cisco;
    private ArrayList<String> midlab;
    private ArrayList<String> sr;
    private ArrayList<String> sm14;

    public ArrayList<String> getCisco()
    {
        return cisco;
    }

    public ArrayList<String> getMidlab()
    {
        return midlab;
    }

    public ArrayList<String> getSr()
    {
        return sr;
    }

    public ArrayList<String> getSm14()
    {
        return sm14;
    }

    public ArrayList<String> getOther()
    {
        return other;
    }

    private ArrayList<String> other;
    private static RequestManager ourInstance = new RequestManager();

    public static RequestManager getInstance()
    {
        return ourInstance;
    }

    private RequestManager()
    {
        cisco = new ArrayList<>();
        midlab = new ArrayList<>();
        sr = new ArrayList<>();
        sm14 = new ArrayList<>();
        other = new ArrayList<>();
        connectServer();
    }

    public void refresh()
    {
        connectServer();
    }

    public void connectServer()
    {
        cisco.clear();
        midlab.clear();
        sr.clear();
        sm14.clear();
        other.clear();
        HashSet<String> ciscoH = new HashSet<>();
        HashSet<String> midH = new HashSet<>();
        HashSet<String> srH = new HashSet<>();
        HashSet<String> sm14H = new HashSet<>();
        HashSet<String> otherH = new HashSet<>();
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
                String userString = user.getLogin() + " " + user.getIp() + " " + user.getPromo();
                if (user.getIp().startsWith("10.224.32."))
                    ciscoH.add(userString);
                else if (user.getIp().startsWith("10.224.33."))
                    midH.add(userString);
                else if (user.getIp().startsWith("10.224.34."))
                    srH.add(userString);
                else if (user.getIp().startsWith("10.224.35."))
                    sm14H.add(userString);
                else
                    otherH.add(userString);
            }
            bufferedReader.close();
            cisco.addAll(ciscoH);
            midlab.addAll(midH);
            sr.addAll(srH);
            sm14.addAll(sm14H);
            other.addAll(otherH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
