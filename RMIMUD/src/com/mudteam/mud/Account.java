package com.company.mud;

import java.util.ArrayList;

public class Account {
    private String username;
    private ArrayList<Character> characters;

    public Account(String username, ArrayList<Character> characters) {
        this.username = username;
        this.characters = characters;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
}
