package com.benja.epistalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import benjamin.epistalk.R;

public class Profile extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTextViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setTextViews()
    {
        User user = getUser();
        if (user == null)
        {
            Toast.makeText(getApplicationContext(), R.string.no_user, Toast.LENGTH_SHORT).show();
            return;
        }
        TextView loginTextView = (TextView) findViewById(R.id.profile_login);
        TextView socketTextView = (TextView) findViewById(R.id.profile_socket);
        TextView ipTextView = (TextView) findViewById(R.id.profile_ip);
        TextView timeConnectionTextView = (TextView) findViewById(R.id.profile_timeConnection);
        TextView lastStatusChangeTextView = (TextView) findViewById(R.id.profile_lastStatusChange);
        TextView inPIETextView = (TextView) findViewById(R.id.profile_inPIE);
        TextView locationTextView = (TextView) findViewById(R.id.profile_location);
        TextView promoTextView = (TextView) findViewById(R.id.profile_promo);
        TextView statusTextView = (TextView) findViewById(R.id.profile_status);
        TextView userdataTextView = (TextView) findViewById(R.id.profile_userdata);
        TextView smTextView = (TextView) findViewById(R.id.profile_sm);

        String loginText = getApplicationContext().getString(R.string.profile_login) + " " + user.getLogin();
        String socketText = getApplicationContext().getString(R.string.profile_socket) + " " + user.getSocket();
        String ipText = getApplicationContext().getString(R.string.profile_ip) + " " + user.getIp();
        String timeConnectionText = getApplicationContext().getString(R.string.profile_timeConnection) + " " + user.getTimeConnection();
        String lastStatusChangeText = getApplicationContext().getString(R.string.profile_lastStatusChange) + " " + user.getLastStatusChange();
        String inPIEText = getApplicationContext().getString(R.string.profile_inPIE) + " " + user.getInPIE();
        String locationText = getApplicationContext().getString(R.string.profile_location) + " " + user.getLocation();
        String promoText = getApplicationContext().getString(R.string.profile_promo) + " " + user.getPromo();
        String statusText = getApplicationContext().getString(R.string.profile_status) + " " + user.getStatus();
        String userDataText = getApplicationContext().getString(R.string.profile_userdata) + " " + user.getUserData();
        String smText = getApplicationContext().getString(R.string.profile_sm) + " " + user.getSm(getApplicationContext());

        assert loginTextView != null;
        assert socketTextView != null;
        assert ipTextView != null;
        assert timeConnectionTextView != null;
        assert lastStatusChangeTextView != null;
        assert inPIETextView != null;
        assert locationTextView != null;
        assert promoTextView != null;
        assert statusTextView != null;
        assert userdataTextView != null;
        assert smTextView != null;

        loginTextView.setText(loginText);
        socketTextView.setText(socketText);
        ipTextView.setText(ipText);
        timeConnectionTextView.setText(timeConnectionText);
        lastStatusChangeTextView.setText(lastStatusChangeText);
        inPIETextView.setText(inPIEText);
        locationTextView.setText(locationText);
        promoTextView.setText(promoText);
        statusTextView.setText(statusText);
        userdataTextView.setText(userDataText);
        smTextView.setText(smText);
    }

    private User getUser()
    {
        Intent intent = getIntent();
        String userLogin =  (String) intent.getExtras().get("ItemLogin");
        User user = null;
        for (User u : RequestManager.getInstance().getMainList())
            if (u.getLogin().equals(userLogin))
                user = u;
        return user;
    }
}