package com.company.database;

import com.company.mud.Account;

public interface AccountRepository {
    public boolean createAccount(String username, String password);
    public boolean login(String username, String password);
    public Account getAccountByID(int id);
}
