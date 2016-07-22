package com.benja.epistalk.tabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benja.epistalk.connection.User;

import java.util.List;

import benjamin.epistalk.R;

public class ListAdapter extends BaseAdapter
{
    private Activity activity;
    private LayoutInflater inflater;
    private List<User> users;

    public ListAdapter(Activity activity, List<User> users)
    {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount()
    {
        return users.size();
    }

    @Override
    public Object getItem(int position)
    {
        return users.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.user_item, parent, false);

        TextView userLogin = (TextView) convertView.findViewById(R.id.list_user_login);
        TextView userPromo = (TextView) convertView.findViewById(R.id.list_user_promo);
        TextView userIP = (TextView) convertView.findViewById(R.id.list_user_ip);

        User user = users.get(position);

        userLogin.setText(user.getLogin());
        userPromo.setText(user.getPromo());
        userIP.setText(user.getIp());

        return convertView;
    }
}
