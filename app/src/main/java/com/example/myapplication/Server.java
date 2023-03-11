package com.example.myapplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    public void server() throws Exception{
        serverSocket = new ServerSocket(53212);

        while(true){

            Socket connectionSocket = serverSocket.accept();

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientInput = inFromClient.readLine();

            outToClient.writeBytes(clientInput);
        }
    }


}
