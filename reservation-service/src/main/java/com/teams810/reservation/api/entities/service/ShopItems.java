package com.teams810.reservation.api.entities.service;

import java.util.List;

public class ShopItems extends ServiceEntity {
    private String owner;
    private List<Item> items;

    public ShopItems() {
    }

    public ShopItems(String owner, List<Item> items) {
        this.owner = owner;
        this.items = items;
    }

    public String getOwner() {
        return owner;
    }

    public List getItems() {
        return items;
    }
}
