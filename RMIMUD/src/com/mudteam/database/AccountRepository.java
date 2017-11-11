package com.company.database;

import com.company.mud.Account;

public interface AccountRepository {
    boolean createAccount(String username, String password);
    boolean login(String username, String password);
    Account getAccountByID(int id);
}
