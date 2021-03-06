package com.mudteam.mud;

import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements IChat {

    private ArrayList<String> messages;

    public ChatServer(boolean isServer) throws RemoteException {
        messages = new ArrayList<>();
    }

    @Override
    public String sendMessage(String message) throws RemoteException {
        messages.add(message);
        return "message received";
    }

    public ArrayList<String> getMessages() throws RemoteException {
        return messages;
    }
}
