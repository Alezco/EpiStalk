package com.benja.epistalk;

import android.os.AsyncTask;
import android.util.Log;

public class ThreadConnect extends AsyncTask<Void, Integer, Void>
{
    @Override
    protected Void doInBackground(Void... params)
    {
        Log.d("Epistalk==============", "Connecting...");
        RequestManager.getInstance().connectServer();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        Log.d("Epistalk==============", String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Log.d("Epistalk==============", "Connected");
    }
}
