package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private Button abschicken;
    private EditText matrikelnummer;
    private TextView serverAntwort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abschicken = findViewById(R.id.button);
        matrikelnummer = findViewById(R.id.login);
        serverAntwort = findViewById(R.id.antwort);

        abschicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTextToServer();
            }
        });

    }

    private void sendTextToServer(){
        sendTextToServerTask toServer = new sendTextToServerTask(this);
        toServer.execute(matrikelnummer.getText().toString());
    }

    private static class sendTextToServerTask extends AsyncTask<String,Void,String> {
        private WeakReference<MainActivity> activityWeakReference;
        private String serverAnswer;

        sendTextToServerTask(MainActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Socket socket = new Socket("se2-isys.aau.at", 53212);
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String userInput = strings[0];
                outToServer.writeBytes(userInput + '\n');
                serverAnswer = inFromServer.readLine();
            } catch (Exception e) {
                e.printStackTrace();

            }
            return serverAnswer;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MainActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()) return;
            activity.serverAntwort.setText(s);

        }
    }
}