package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LocationSQLiteContextTest {
    LocationRepository locationRepo;
    Character character;

    @Before
    public void setUp() throws Exception {
        locationRepo = new LocationSQLiteContext();
        character = new Character("Redmar");
    }

    @Test
    public void getLocations() throws Exception {
        ArrayList<Location> locations = locationRepo.getLocations();

        assertNotEquals(locations, null);
    }

    @Test
    public void getLocationByCharacter() throws Exception {
        Location location = locationRepo.getLocationByCharacter(character);

        assertEquals(location, null);
    }

}