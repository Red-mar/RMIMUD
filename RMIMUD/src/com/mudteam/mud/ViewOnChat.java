package com.mudteam.mud;

import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ViewOnChat extends UnicastRemoteObject implements IRemotePropertyListener{
    private IRemotePublisherForListener publisher;

    public ViewOnChat() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 4322);
        publisher = (IRemotePublisherForListener) registry.lookup("chatPublisher");
        publisher.subscribeRemoteListener(this, "chat");
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        System.out.println(propertyChangeEvent.getNewValue());
    }
}
