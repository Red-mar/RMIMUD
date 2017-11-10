package com.company.network;

import com.company.mud.Character;
import com.company.mud.Location;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote {
    void logout() throws RemoteException;
    Character getCharacter() throws RemoteException;
    Location[][] loadMap() throws RemoteException;
}
