package com.teams810.reservation.api.entities;

public enum ReservationStatus {
    WAITING("Waiting"), // Good Case: Customer before receiving reserved goods or services.
    SUCCESS("Success"), // Good Case: Customer received goods or services successfully.
    MISSED("Missed"),   // Bad Case:  Customer did not show up on time.
    CANCELLED_BY_USER("Cancelled by user"), // Bad Case:  Customer cancelled the reservation.
    CANCELLED_BY_SHOP("Cancelled by shop"); // Bad Case:  Shop owner cancelled customer's reservation (for some reasons).

    private final String displayValue;

    private ReservationStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }
}
