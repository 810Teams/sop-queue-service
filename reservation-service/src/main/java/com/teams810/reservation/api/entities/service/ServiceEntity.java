package com.teams810.reservation.api.entities.service;

public abstract class ServiceEntity {
    private String _id;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String get__v() {
        return __v;
    }
}
