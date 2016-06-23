package com.benja.epistalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import benjamin.epistalk.R;

public class TabFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tabs, container, false);
        final ListView list_sm = (ListView) rootView.findViewById(R.id.list_sm);
        TextView textView = (TextView) rootView.findViewById(R.id.textView9);
        ArrayList<String> arrayList = new ArrayList<>();

        int pageNum = this.getArguments().getInt("pagenum");
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
        java.util.Collections.sort(arrayList);
        if (arrayList.size() == 0)
            textView.setVisibility(View.VISIBLE);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);
        assert list_sm != null;
        list_sm.setAdapter(arrayAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new ThreadConnect().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright);

        return rootView;
    }
}
