package com.company.database;

import com.company.mud.Character;

public interface CharacterRepository {
    Character getCharacterByName(String name);
    boolean createCharacter(String name);
    boolean editCharacter(int id, String name);
}
