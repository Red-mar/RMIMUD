package com.company.network;

import com.company.mud.IChat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIClient {
    private static final String bindingName = "Chat";

    private Registry registry = null;
    private IChat chat = null;

    // Constructor
    public RMIClient(String ipAddress, int portNumber) {

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        if (registry != null) {
            try {
                chat = (IChat) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: RemoteException: " + ex.getMessage());
            } catch (NotBoundException ex) {
                System.out.println("Client: NotBoundException: " + ex.getMessage());
            }
        }

    }

    public void sendMessage(String message) throws RemoteException {
        chat.sendMessage(message);
    }
}
