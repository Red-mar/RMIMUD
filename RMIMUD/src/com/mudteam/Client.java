package com.mudteam;

import com.mudteam.mud.GameClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        GameClient client = null;

        System.out.println("Client using registry");

        String ip = "localhost";
        int port = 4321;

        try {
            client = new GameClient(ip, port);
        } catch (RemoteException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
            System.exit(0);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
