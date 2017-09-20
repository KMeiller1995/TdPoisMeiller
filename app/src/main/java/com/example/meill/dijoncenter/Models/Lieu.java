package com.example.meill.dijoncenter.Models;

import java.util.UUID;

/**
 * Created by meill on 08/09/2017.
 */
public class Lieu {
    UUID id;
    String type;
    String name;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    Location location;
}
