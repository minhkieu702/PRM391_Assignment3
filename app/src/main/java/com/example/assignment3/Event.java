package com.example.assignment3;

import java.io.Serializable;

public class Event implements Serializable {
    private String name;
    private String time;
    private String location;
    private String description;

    public Event(String name, String time, String location, String description) {
        this.name = name;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

