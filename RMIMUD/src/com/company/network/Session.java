package com.company.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote {
    void logout() throws RemoteException;
    String getName() throws RemoteException;
}
