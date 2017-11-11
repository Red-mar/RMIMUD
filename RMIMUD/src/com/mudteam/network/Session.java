package com.mudteam.network;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Session extends Remote {
    void logout() throws RemoteException;
    Character getCharacter() throws RemoteException;
    ArrayList<Location> loadMap() throws RemoteException;
}
