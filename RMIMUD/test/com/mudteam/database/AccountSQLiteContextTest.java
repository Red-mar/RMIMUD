package com.mudteam.database;

import com.mudteam.mud.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountSQLiteContextTest {
    AccountRepository accountRepo;
    int id;

    @Before
    public void setUp() throws Exception {
        accountRepo = new AccountSQLiteContext();
        id = 1;
    }

    @Test
    public void login() throws Exception {
        boolean isSuccesful = accountRepo.login("foiwejow", "gorejge");

        assertEquals(false, isSuccesful);
    }

    @Test
    public void getAccountByID() throws Exception {
        Account account = accountRepo.getAccountByID(id);

        assertNotEquals(null, account);
    }

}