package com.mudteam.mud;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {
    private String name = "Unkown location";
    private ArrayList<Character> characters;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character character){
        characters.add(character);
    }
}
