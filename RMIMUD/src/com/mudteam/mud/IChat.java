package com.mudteam.mud;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
    String sendMessage(String message) throws RemoteException;
}
