package com.benja.epistalk;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import benjamin.epistalk.R;

public class MainActivity extends AppCompatActivity
{
    private static ViewPager viewPager;
    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getBaseContext());
        if (viewPager != null)
        {
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setOffscreenPageLimit(0);
        }

        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //int id = item.getItemId();
        switch (item.getItemId())
        {
            /*case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Settings not implemented yet", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.refresh:
                RequestManager.getInstance().refresh();
                if (toast != null)
                    toast.cancel();
                toast = Toast.makeText(MainActivity.this, getResources().getString(R.string.refreshing), Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return /*id == R.id.action_settings ||*/ super.onOptionsItemSelected(item);
    }
}

