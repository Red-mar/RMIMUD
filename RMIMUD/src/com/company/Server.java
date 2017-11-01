package com.company;

import com.company.mud.GameServer;
import java.rmi.RemoteException;
import java.util.Timer;

public class Server {

    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            timer.schedule(new GameServer(), 0, 1000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
