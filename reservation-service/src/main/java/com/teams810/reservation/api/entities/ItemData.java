package com.teams810.reservation.api.entities;

import java.io.Serializable;

public class ItemData implements Serializable {
    private String itemId;
    private int amount;

    public ItemData() {
    }

    public ItemData(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}
