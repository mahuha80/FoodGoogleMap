package com.example.googlemap;

import com.google.gson.annotations.SerializedName;

class Geometry {
    @SerializedName("location")
    private Location location;

    public Geometry(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
