package com.mudteam.network;

import com.mudteam.mud.ChatServer;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    private static final int portNumberPush = 4321;

    private static final String bindingNamePush = "Chat";

    private Registry registryPush = null;
    private ChatServer chatServer = null;
    private LoginImp login = null;

    public RMIServer() {
        try {
            chatServer = new ChatServer(true);
            login = new LoginImp();
            System.out.println("Server: Chat created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create chat");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            chatServer = null;
        }

        try {
            registryPush = LocateRegistry.createRegistry(portNumberPush);
            System.out.println("Server: Registry created on port number ");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registryPush = null;
        }

        try {
            registryPush.rebind(bindingNamePush, chatServer);
            registryPush.rebind("login", login);
            System.out.println("Binding chat");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind chat");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    public ChatServer getChatServer() {
        return chatServer;
    }
}
