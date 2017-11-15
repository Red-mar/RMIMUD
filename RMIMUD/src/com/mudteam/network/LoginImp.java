package com.mudteam.network;

import com.mudteam.database.AccountRepository;
import com.mudteam.database.AccountSQLiteContext;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImp extends UnicastRemoteObject implements Login {
    private AccountRepository accountRepo;

    protected LoginImp() throws RemoteException {
        accountRepo = new AccountSQLiteContext();
    }

    @Override
    public Session login(String username, String password) throws LoginException, RemoteException {
        int accountID = accountRepo.login(username, password);
        if (accountID != 0){
            return new SessionImp(accountID);
        }
        return null;
    }

    @Override
    public boolean createAccount(String username, String password) throws RemoteException {
        if (accountRepo.createAccount(username, password)){
            return true;
        }
        return false;
    }
}
