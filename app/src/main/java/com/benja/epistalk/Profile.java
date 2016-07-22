package com.benja.epistalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setTextViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    private void setTextViews()
    {
        Context context = getApplicationContext();
        User user = getUser();
        if (user == null)
        {
            Toast.makeText(context, R.string.no_user, Toast.LENGTH_SHORT).show();
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

        String loginText = context.getString(R.string.profile_login) + " " + user.getLogin();
        String socketText = context.getString(R.string.profile_socket) + " " + user.getSocket();
        String ipText = context.getString(R.string.profile_ip) + " " + user.getIp();
        String timeConnectionText = context.getString(R.string.profile_timeConnection) + " " + user.getTimeConnectionAsString(context);
        String lastStatusChangeText = context.getString(R.string.profile_lastStatusChange) + " " + user.getLastStatusChangeAsString(context);
        String inPIEText = context.getString(R.string.profile_inPIE) + " " + user.getInPIEAsString(context);
        String locationText = context.getString(R.string.profile_location) + " " + user.getLocationAsString(context);
        String promoText = context.getString(R.string.profile_promo) + " " + user.getPromo();
        String statusText = context.getString(R.string.profile_status) + " " + user.getStatusAsString();
        String userDataText = context.getString(R.string.profile_userdata) + " " + user.getUserDataAsString(context);
        String smText = context.getString(R.string.profile_sm) + " " + user.getSm(context);

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
        String userIp =  (String) intent.getExtras().get("ItemIp");
        User user = null;
        for (User u : RequestManager.getInstance().getMainList())
            if (u.getIp().equals(userIp))
                user = u;
        return user;
    }
}