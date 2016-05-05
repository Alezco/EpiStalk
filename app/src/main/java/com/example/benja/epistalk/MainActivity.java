package com.example.benja.epistalk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    private ListView list_sm;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list_sm = (ListView) findViewById(R.id.list_sm);

        arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        list_sm.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Add favorites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        connectServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void connectServer()
    {
        arrayList.add("walala");
        try
        {
            Socket socket = new Socket("ns-server.epita.fr", 4242);
            OutputStream out =  socket.getOutputStream();
            InputStream in = socket.getInputStream();
            Scanner s = new Scanner(System.in);
            System.out.print("> ");
            String msg = s.nextLine() + "\n";
            for (int i = 0; i < msg.length(); i++)
                out.write(msg.charAt(i));
            Scanner s2 = new Scanner(in);
            System.out.println("server: " + s2.nextLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /*

    NS_SERVER = 'ns-server.epita.fr'
    NS_PORT = 4242

    ING1_PROMO = 'epita_2018'

    class User(object):
      def __init__(self, line):
        fields = line.split()
        self.login = fields[1]
        self.ip = fields[2]
        self.promo = fields[9]

      @property
      def sm(self):
        if self.ip.startswith('10.41'):
          return "sm random"
        else:
          return None

      def __cmp__(self, other):
        return cmp(self.login, other.login)

      def __hash__(self):
        return hash(self.login)

    def connect_to_ns(server, port):
      s = socket.socket()
      s.connect((server, port))
      s.setblocking(0)
      ready = select.select([s], [], [], 0.5)
      if ready[0]:
        s.recv(8192) # salut ...
      else:
        return None
      return s

    def list_users(sock):
      sock.send(b"list_users\n")
      buf = ''
      while True:
        sock.setblocking(0)
        ready = select.select([sock], [], [], 0.5)
        if ready[0]:
          tmp = sock.recv(8192)
        else:
          return []
        buf += tmp.decode('utf-8')
        if b'\nrep 002' in tmp or tmp == b'':
          break
      return buf.split('\n')[:-2]

    def nb_connected():
      sock = connect_to_ns(NS_SERVER, NS_PORT)
      if sock is None:
        return 0
      users = (User(l) for l in list_users(sock))
      promo = (u for u in users if u.promo == ING1_PROMO)
      promo_in_sm = (u for u in promo if u.sm is not None)
      return len(list(promo_in_sm))


         */
}

