package com.company.mud;

import com.company.network.RMIClient;
import com.company.network.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GameClient {

    private RMIClient client;

    public GameClient(String ip, int port) throws RemoteException, NotBoundException {
        client = new RMIClient(ip, port);
        ViewOnChat viewOnChat = new ViewOnChat();
    }

    public RMIClient getClient() {
        return client;
    }
}
