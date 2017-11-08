package com.company.mud;

import com.sun.javafx.geom.Vec2d;

import java.io.Serializable;

public class Location implements Serializable {
    private String name;
    private Vec2d location;

    public Location(String name, Vec2d location) {
        this.name = name;
        this.location = location;
    }
}
