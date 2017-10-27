package com.company.mud;

import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements IChat {

    private transient RemotePublisher publisher;
    private ArrayList<String> messages;
    private boolean isServer;

    public ChatServer(boolean isServer) throws RemoteException {
        messages = new ArrayList<>();
        this.isServer = isServer;
        if (isServer){
            publisher = new RemotePublisher();
            publisher.registerProperty("chat");
            Registry registry = LocateRegistry.createRegistry(4322);
            registry.rebind("chatPublisher", publisher);
        }
    }

    @Override
    public String sendMessage(String message) throws RemoteException {
        messages.add(message);
        if (isServer){
            publisher.inform("chat", null, message);
        }
        return "message received";
    }

    @Override
    public ArrayList<String> getMessages() throws RemoteException {
        return messages;
    }
}
