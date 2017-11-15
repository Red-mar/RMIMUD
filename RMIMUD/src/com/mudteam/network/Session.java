package com.mudteam.network;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Session extends Remote {
    void logout() throws RemoteException;
    ArrayList<Character> getCharacters() throws RemoteException;
    boolean CreateCharacter(String name) throws RemoteException;
    ArrayList<Location> loadMap() throws RemoteException;
}
