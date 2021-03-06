package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Character> getCharacterByAccount(int id) {
        ArrayList<Character> characters = new ArrayList<>();
        String command = "SELECT * FROM Character" +
                " JOIN accountcharacter ON character.ID = accountcharacter.CharacterID" +
                " JOIN account ON accountcharacter.AccountID = account.ID" +
                " WHERE account.ID = ?";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                characters.add(CreateCharacterFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public ArrayList<Character> getCharactersByLocation(Location location) {
        ArrayList<Character> characters = new ArrayList<>();
        String command = "SELECT * FROM Character" +
                " JOIN characterlocation ON character.ID = characterlocation.CharacterID" +
                " JOIN location ON characterlocation.LocationID = location.ID" +
                " WHERE location.Name = ?";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, location.getName());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                characters.add(CreateCharacterFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public boolean createCharacter(int accountID, String name) {
        String command = "INSERT INTO character (Name, Lifepoints) VALUES (?, 10)";
        int characterID;

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, name);
            statement.execute();

            command = "SELECT ID FROM character ORDER BY ID DESC LIMIT 1";
            statement = conn.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            characterID = rs.getInt("ID");

            command = "INSERT INTO accountcharacter (AccountID, CharacterID) VALUES (?, ?)";
            statement = conn.prepareStatement(command);
            statement.setInt(1, accountID);
            statement.setInt(2, characterID);
            statement.execute();

            command = "INSERT INTO characterlocation (LocationID, CharacterID) VALUES (1, ?)";
            statement = conn.prepareStatement(command);
            statement.setInt(1, characterID);
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

    private Character CreateCharacterFromResultSet(ResultSet rs) throws SQLException {
        return new Character(
                rs.getString("Name")
        );
    }
}
