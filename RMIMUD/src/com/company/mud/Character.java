package com.company.mud;

import java.io.Serializable;

public class Character implements Serializable {
    String name = "Undefined";

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
