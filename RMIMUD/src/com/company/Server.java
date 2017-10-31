package com.company;

import com.company.mud.Game;
import com.company.network.RMIServer;

import java.rmi.RemoteException;
import java.util.Timer;

public class Server {

    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            timer.schedule(new Game(), 0, 1000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
