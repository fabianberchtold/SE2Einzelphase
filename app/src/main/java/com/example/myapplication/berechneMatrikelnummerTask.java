package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;

public class berechneMatrikelnummerTask extends AsyncTask<String,Void,String> {
    TextView serverAntwort;

    berechneMatrikelnummerTask(TextView serverAntwort){
        this.serverAntwort = serverAntwort;
    }

    @Override
    protected String doInBackground(String... strings) {




        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
