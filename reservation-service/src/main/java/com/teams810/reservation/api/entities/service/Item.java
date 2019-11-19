package com.teams810.reservation.api.entities.service;

public class Item extends ServiceEntity {
    private String name;
    private int price;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
