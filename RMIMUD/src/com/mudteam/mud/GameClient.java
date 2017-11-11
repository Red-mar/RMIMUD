package com.company.mud;

import com.company.network.RMIClient;
import com.company.network.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GameClient {

    private RMIClient client;
    private Session session;
    private Location[][] map;

    public GameClient(String ip, int port) throws RemoteException, NotBoundException {
        client = new RMIClient(ip, port);
        ViewOnChat viewOnChat = new ViewOnChat();
    }

    public RMIClient getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setMap(Location[][] map) {
        this.map = map;
    }

    public Location[][] getMap() {
        return map;
    }
}
