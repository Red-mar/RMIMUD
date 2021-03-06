package com.mudteam.network;

import com.mudteam.database.CharacterRepository;
import com.mudteam.database.CharacterSQLiteContext;
import com.mudteam.database.LocationRepository;
import com.mudteam.database.LocationSQLiteContext;
import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;

public class SessionImp extends UnicastRemoteObject implements Session, Unreferenced {
    LocationRepository locationRepo;
    CharacterRepository characterRepo;
    int accountID;

    public SessionImp(int accountID) throws RemoteException {
        locationRepo = new LocationSQLiteContext();
        characterRepo = new CharacterSQLiteContext();
        this.accountID = accountID;
    }

    @Override
    public ArrayList<Location> loadMap() throws RemoteException {
        return locationRepo.getLocations();
    }

    @Override
    public void logout() throws RemoteException {
        unexportObject(this,true);
    }

    @Override
    public ArrayList<Character> getCharacters() throws RemoteException {
        return characterRepo.getCharacterByAccount(accountID);
    }

    @Override
    public boolean CreateCharacter(String name) throws RemoteException {
        return characterRepo.createCharacter(accountID, name);
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
