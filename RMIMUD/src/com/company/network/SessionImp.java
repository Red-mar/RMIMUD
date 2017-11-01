package com.company.network;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class SessionImp extends UnicastRemoteObject implements Session, Unreferenced {
    String name = "Undefined";

    public SessionImp(String name) throws RemoteException {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void logout() throws RemoteException {
        unexportObject(this,true);
    }

    @Override
    public void unreferenced() {
        try {
            unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
