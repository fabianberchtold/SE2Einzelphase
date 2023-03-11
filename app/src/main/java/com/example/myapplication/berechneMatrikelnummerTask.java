package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class berechneMatrikelnummerTask extends AsyncTask<String,Void,String> {
    private WeakReference<MainActivity> activityWeakReference;

    berechneMatrikelnummerTask(MainActivity activity){
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected String doInBackground(String... strings) {
        char[] matrikelnummer = strings[0].toCharArray();
        return sortNonPrimeDigits(matrikelnummer);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity activity = activityWeakReference.get();
        if(activity == null||activity.isFinishing()) return;
        TextView antwort = activity.findViewById(R.id.antwort);
        antwort.setText(s);
    }

    private String sortNonPrimeDigits(char[] matrikelnummer){
        int[] occurencesOfDigit = new int[6];
        char[] sortedMatrikelnummer;

        for (char c : matrikelnummer) {
            switch (c) {
                case '0':
                    occurencesOfDigit[0] += 1;
                    break;
                case '1':
                    occurencesOfDigit[1] += 1;
                    break;
                case '4':
                    occurencesOfDigit[2] += 1;
                    break;
                case '6':
                    occurencesOfDigit[3] += 1;
                    break;
                case '8':
                    occurencesOfDigit[4] += 1;
                    break;
                case '9':
                    occurencesOfDigit[5] += 1;
                    break;
            }
        }

        for (int i = 1; i < occurencesOfDigit.length; i++) {
            occurencesOfDigit[i] += occurencesOfDigit[i-1];
        }
        sortedMatrikelnummer = new char[occurencesOfDigit[5]];
        for (int i = 5; i > 0; i--) {
            occurencesOfDigit[i] = occurencesOfDigit[i-1];
        }
        occurencesOfDigit[0] = 0;
        for (char c : matrikelnummer) {
            switch (c) {
                case '0':
                    sortedMatrikelnummer[occurencesOfDigit[0]] = '0';
                    occurencesOfDigit[0] += 1;
                    break;
                case '1':
                    sortedMatrikelnummer[occurencesOfDigit[1]] = '1';
                    occurencesOfDigit[1] += 1;
                    break;
                case '4':
                    sortedMatrikelnummer[occurencesOfDigit[2]] = '4';
                    occurencesOfDigit[2] += 1;
                    break;
                case '6':
                    sortedMatrikelnummer[occurencesOfDigit[3]] = '6';
                    occurencesOfDigit[3] += 1;
                    break;
                case '8':
                    sortedMatrikelnummer[occurencesOfDigit[4]] = '8';
                    occurencesOfDigit[4] += 1;
                    break;
                case '9':
                    sortedMatrikelnummer[occurencesOfDigit[5]] = '9';
                    occurencesOfDigit[5] += 1;
                    break;
            }
        }

        return new String(sortedMatrikelnummer);

    }

}
