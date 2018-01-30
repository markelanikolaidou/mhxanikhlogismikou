package com.softeng.athleticsticket.model;

public class SimpleGate {

    private int id;
    private String name;
    private int capacity;
    private int occupied;

    public SimpleGate() {
        // Default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(final int occupied) {
        this.occupied = occupied;
    }
}
