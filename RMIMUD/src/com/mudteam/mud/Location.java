package com.mudteam.mud;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {
    private String name = "Unkown location";
    private ArrayList<Character> characters;
    private int x;
    private int y;

    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
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
