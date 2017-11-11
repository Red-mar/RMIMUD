package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.util.ArrayList;

public interface LocationRepository {
    ArrayList<Location> getLocations();
    Location getLocationByCharacter(Character character);
}
