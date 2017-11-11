package com.company.mud;

import java.io.Serializable;

public class Character implements Serializable {
    private String name = "Undefined";
    private Location location;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
