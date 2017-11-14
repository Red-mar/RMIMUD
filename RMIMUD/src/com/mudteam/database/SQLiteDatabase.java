package com.mudteam.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {

    private static String connectionString = "jdbc:sqlite:C:\\Users\\a\\Desktop\\git\\RMIMUD\\RMIMUD\\RMIMUD\\src\\com\\mudteam\\database\\database";

    public static Connection connection(){
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
