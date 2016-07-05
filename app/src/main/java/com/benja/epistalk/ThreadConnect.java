package com.benja.epistalk;

import android.os.AsyncTask;

public class ThreadConnect extends AsyncTask<Void, Integer, Void>
{
    @Override
    protected Void doInBackground(Void... params)
    {
        RequestManager.getInstance().connectServer();
        RequestManager.getInstance().doRefresh();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
    }
}
