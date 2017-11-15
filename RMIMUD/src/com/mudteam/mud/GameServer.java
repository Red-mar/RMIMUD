package com.mudteam.mud;

import com.mudteam.database.CharacterRepository;
import com.mudteam.database.CharacterSQLiteContext;
import com.mudteam.network.RMIServer;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.TimerTask;

public class GameServer extends TimerTask {
    private RemotePublisher publisher;
    private RMIServer server;
    private int ticks = 0;

    public GameServer() throws RemoteException {
        server = new RMIServer();
        publisher = new RemotePublisher();
        publisher.registerProperty("chat");
        Registry registry = LocateRegistry.createRegistry(4322);
        registry.rebind("chatPublisher", publisher);
    }

    @Override
    public void run() {
        ticks++;
        System.out.println("Processing game tick: " + ticks);
        try {
            for (String s : server.getChatServer().getMessages()) {
                publisher.inform("chat", null, s);
            }
            server.getChatServer().getMessages().clear();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message){

    }
}
