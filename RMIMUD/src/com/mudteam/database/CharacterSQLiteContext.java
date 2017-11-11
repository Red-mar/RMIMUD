package com.company.database;

import com.company.mud.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterSQLiteContext implements CharacterRepository {

    @Override
    public Character getCharacterByName(String name) {
        String command = "SELECT * FROM character WHERE Name = ?";
        Character character = null;

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                character = new Character(rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return character;
    }

    @Override
    public boolean createCharacter(String name) {
        String command = "INSERT INTO character (Name, Lifepoints) VALUES (?, 10)";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, name);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editCharacter(int id, String name) {
        String command = "UPDATE character SET Name = ? WHERE ID = ?";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}