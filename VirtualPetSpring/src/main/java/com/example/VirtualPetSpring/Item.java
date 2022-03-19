package com.example.VirtualPetSpring;

public class Item {
    private String name;
    private int points;
    private float prize;

    public Item(String name, int points, float prize){
        this.name = name;
        this.points = points;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
