package com.company.network;

import com.company.mud.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote {
    void logout() throws RemoteException;
    Character getCharacter() throws RemoteException;
}
