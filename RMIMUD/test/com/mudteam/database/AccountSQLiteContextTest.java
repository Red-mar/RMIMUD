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
        int isSuccesful = accountRepo.login("foiwejow", "gorejge");

        assertEquals(0, isSuccesful);
    }

    @Test
    public void getAccountByID() throws Exception {
        Account account = accountRepo.getAccountByID(id);

        assertNotEquals(null, account);
    }

}