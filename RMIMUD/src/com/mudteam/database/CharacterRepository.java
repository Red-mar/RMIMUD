package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;

import java.util.ArrayList;

public interface CharacterRepository {
    Character getCharacterByName(String name);
    ArrayList<Character> getCharactersByLocation(Location location);
    boolean createCharacter(String name);
    boolean editCharacter(int id, String name);
}
