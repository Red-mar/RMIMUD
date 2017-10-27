package com.company.mud;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IChat extends Remote {
    String sendMessage(String message) throws RemoteException;
    ArrayList<String> getMessages() throws RemoteException;
}
