package com.company.network;

import com.company.mud.Character;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class SessionImp extends UnicastRemoteObject implements Session, Unreferenced {
    Character character;

    public SessionImp(String name) throws RemoteException {
        character = new Character(name);
    }

    public Character getCharacter() {
        return character;
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
