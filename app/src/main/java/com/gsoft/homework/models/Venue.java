package com.gsoft.homework.models;

public class Venue {
    public String fsqId, name;
    public Location location;

    public Venue(String id, String name, Location location) {
        this.fsqId = id;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}

