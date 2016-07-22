package com.benja.epistalk.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.benja.epistalk.profile.Profile;
import com.benja.epistalk.connection.RequestManager;
import com.benja.epistalk.connection.Resfreshable;
import com.benja.epistalk.connection.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import benjamin.epistalk.R;

public class TabFragment extends Fragment implements Resfreshable
{
    private TextView textView;
    private ImageView imageView;
    private int pageNum;
    private List<User> arrayList;
    private ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tabs, container, false);
        listview = (ListView) rootView.findViewById(R.id.list_sm);
        textView = (TextView) rootView.findViewById(R.id.textView_noone);
        imageView = (ImageView) rootView.findViewById(R.id.noone_sm);
        arrayList = new ArrayList<>();

        pageNum = this.getArguments().getInt("pageNum");
        /*final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                RequestManager.getInstance().doRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(R.color.PrimaryColor);*/
        handleProfiles(listview);
        return rootView;
    }

    @Override
    public void refresh()
    {
        if (pageNum == 1)
            arrayList = RequestManager.getInstance().getCisco();
        if (pageNum == 2)
            arrayList = RequestManager.getInstance().getMidlab();
        if (pageNum == 3)
            arrayList = RequestManager.getInstance().getSr();
        if (pageNum == 4)
            arrayList = RequestManager.getInstance().getSm14();
        if (pageNum == 5)
            arrayList = RequestManager.getInstance().getOther();
        sortListByLogin();
        if (arrayList.size() == 0)
        {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }
        ListAdapter listAdapter = new ListAdapter(getActivity(), arrayList);
        assert listview != null;
        listview.setAdapter(listAdapter);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        RequestManager.getInstance().unregisterRefreshable(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        RequestManager.getInstance().registerRefreshable(this);
        this.refresh();
    }

    private void sortListByLogin()
    {
        java.util.Collections.sort(arrayList, new Comparator<User>()
        {
            @Override
            public int compare(User lhs, User rhs)
            {
                return lhs.getLogin().compareTo(rhs.getLogin());
            }
        });
    }

    private void sortListByPromo()
    {
        java.util.Collections.sort(arrayList, new Comparator<User>()
        {
            @Override
            public int compare(User lhs, User rhs)
            {
                int tmp = lhs.getPromo().compareTo(rhs.getPromo());
                if (tmp == 0)
                    return lhs.getLogin().compareTo(rhs.getLogin());
                return tmp;
            }
        });
    }

    private void sortListByIp()
    {
        java.util.Collections.sort(arrayList, new Comparator<User>()
        {
            @Override
            public int compare(User lhs, User rhs)
            {
                int tmp = lhs.getIp().compareTo(rhs.getIp());
                if (tmp == 0)
                    return lhs.getLogin().compareTo(rhs.getLogin());
                return tmp;
            }
        });
    }

    private void handleProfiles(final ListView listview)
    {
       listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
       {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id)
           {
               User user = (User) listview.getItemAtPosition(position);
               Intent intent = new Intent(getContext(), Profile.class);
               intent.putExtra("ItemIp", user.getIp());
               startActivityForResult(intent, 0);
           }
       });
    }
}