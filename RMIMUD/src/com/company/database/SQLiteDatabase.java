package com.company.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {

    private static String connectionString = "jdbc:sqlite:C:\\Users\\redmar\\Desktop\\git\\RMIMUD\\RMIMUD\\src\\com\\company\\database\\database";

    public static Connection connection(){
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
