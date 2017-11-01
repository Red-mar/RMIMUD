package com.company.network;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImp extends UnicastRemoteObject implements Login {
    protected LoginImp() throws RemoteException {
    }

    @Override
    public Session login(String username, String password) throws LoginException, RemoteException {
        if (true){
        //if (username.equals("Redmar") && password.equals("")){
            return new SessionImp(username);
        }
        return null;
    }
}
