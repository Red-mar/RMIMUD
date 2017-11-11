package com.mudteam.network;

import com.mudteam.mud.IChat;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

    public Session login(String username, String password){
        try {
            Login remote = (Login) Naming.lookup("//localhost:4321/login");
            Session session = remote.login(username,password);
            return session;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean createAccount(String username, String password){
        try {
            Login remote = (Login) Naming.lookup("//localhost:4321/login");
            return remote.createAccount(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void logout(){

    }

    public void sendMessage(String message) throws RemoteException {
        chat.sendMessage(message);
    }
}
