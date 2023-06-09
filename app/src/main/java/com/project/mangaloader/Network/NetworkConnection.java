package com.project.mangaloader.Network;

import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnection extends AsyncTask<String, String, String> {

    private Context context;

    public NetworkConnection(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {


        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL("http://myloader1.site/");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1000); // mTimeout is in seconds
                if (urlc.getResponseCode() == 200) {
                    urlc.disconnect();


                } else {

                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                this.cancel(true);
            }

        }

        return null;

    }


    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection = new NetworkErrorConnection(context);

    }
}
