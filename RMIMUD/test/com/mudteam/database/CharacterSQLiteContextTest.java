package com.mudteam.database;

import com.mudteam.mud.Character;
import com.mudteam.mud.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CharacterSQLiteContextTest {
    CharacterRepository characterRepo;
    String name;
    Location location;

    @Before
    public void setUp() throws Exception {
        characterRepo = new CharacterSQLiteContext();
        name = "noname";
        location = new Location("test");
    }

    @Test
    public void getCharacterByName() throws Exception {
        Character character = characterRepo.getCharacterByName(name);

        assertEquals(character, null);
    }

    @Test
    public void getCharactersByLocation() throws Exception {
        ArrayList<Character> test = new ArrayList<>();
        ArrayList<Character> characters = characterRepo.getCharactersByLocation(location);

        assertEquals(characters, test);
    }

    //@Test
    //public void createCharacter() throws Exception {
    //}

    //@Test
    //public void editCharacter() throws Exception {
    //}

}