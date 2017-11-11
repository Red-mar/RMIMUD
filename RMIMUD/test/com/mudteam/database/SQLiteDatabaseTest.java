package com.mudteam.database;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class SQLiteDatabaseTest {
    @Test
    public void connection() throws Exception {
        Connection conn = SQLiteDatabase.connection();

        assertNotEquals(conn, null);
    }

}