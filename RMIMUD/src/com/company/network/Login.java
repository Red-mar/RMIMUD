package com.company.network;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

//https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
// Remote session pattern.

public interface Login extends Remote {
    Session login(String username, String password)
            throws LoginException, RemoteException;
}
