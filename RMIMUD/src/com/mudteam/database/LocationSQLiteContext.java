package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationSQLiteContext implements LocationRepository {
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        String command = "SELECT * FROM location";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
             ResultSet rs = statement.executeQuery();

             while (rs.next()){
                 locations.add(CreateLocationFromResultSet(rs));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public Location getLocationByCharacter(Character character) {
        Location location = null;
        String command = "SELECT * FROM location " +
                "JOIN characterlocation ON location.ID = characterlocation.LocationID " +
                "JOIN character ON characterlocation.CharacterID = character.ID " +
                "WHERE character.Name = ?";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, character.getName());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                location = CreateLocationFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    private Location CreateLocationFromResultSet(ResultSet rs) throws SQLException {
        return new Location(
                rs.getString("Name")
        );
    }
}
