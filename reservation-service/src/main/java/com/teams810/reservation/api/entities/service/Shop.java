package com.teams810.reservation.api.entities.service;

import java.util.List;

public class Shop extends ServiceEntity {
    private String shopname;
    private String description;
    private String tel;
    private String address;
    private String rating;
    private String owner;

    public Shop() {
    }

    public Shop(String shopname, String description, String tel, String address, String rating, String owner) {
        this.shopname = shopname;
        this.description = description;
        this.tel = tel;
        this.address = address;
        this.rating = rating;
        this.owner = owner;
    }

    public String getShopname() {
        return shopname;
    }

    public String getDescription() {
        return description;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public String getOwner() {
        return owner;
    }
}