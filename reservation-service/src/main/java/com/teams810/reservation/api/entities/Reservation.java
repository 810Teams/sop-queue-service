package com.teams810.reservation.api.entities;

import com.teams810.reservation.api.exceptions.InvalidStatusFlowException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userId;
    private String itemId;
    private int amount;
    private TimePeriod timePeriod;
    private ReservationStatus status;
    private String message;

    public Reservation() {
        // Constructor: Default constructor
        this.status = ReservationStatus.WAITING;
        this.message = "";
    }

    public Reservation(String userId, String itemId, TimePeriod timePeriod) {
        // Constructor: Single amount without message
        this(userId, itemId, timePeriod, "");
    }

    public Reservation(String userId, String itemId, TimePeriod timePeriod, String message) {
        // Constructor: Single amount with message
        this(userId, itemId, 1, timePeriod, message);
    }

    public Reservation(String userId, String itemId, int amount, TimePeriod timePeriod) {
        // Constructor: Multiple amount without message
        this(userId, itemId, amount, timePeriod, "");
    }

    public Reservation(String userId, String itemId, int amount, TimePeriod timePeriod, String message) {
        // Constructor: Multiple amount with message
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.status = ReservationStatus.WAITING;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void checkSuccess() throws InvalidStatusFlowException {
        // Instance Method: Shop & user checks reservation as success
        if (this.status != ReservationStatus.WAITING) {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
        this.status = ReservationStatus.SUCCESS;
    }

    public void checkMissed() throws InvalidStatusFlowException {
        // Instance Method: Shop checks reservation as missed
        if (this.status != ReservationStatus.WAITING) {
            throw new InvalidStatusFlowException("Invalid status flow.");
        } else if (LocalDateTime.now().compareTo(this.timePeriod.getEndDateTime()) < 0) {
            throw new InvalidStatusFlowException("Unable to check as missed before the reserved time.");
        }
        this.status = ReservationStatus.MISSED;
    }

    public void cancelByUser() throws InvalidStatusFlowException {
        // Instance Method: User cancels reservation
        if (this.status != ReservationStatus.WAITING) {
            throw new InvalidStatusFlowException("Invalid status flow.");
        } else if (LocalDateTime.now().compareTo(this.timePeriod.getStartDateTime()) >= 0) {
            throw new InvalidStatusFlowException("Unable to cancel after the reserved time.");
        }
        this.status = ReservationStatus.CANCELLED_BY_USER;
    }

    public void cancelByShop() throws InvalidStatusFlowException {
        // Instance Method: Shop cancels reservation
        if (this.status != ReservationStatus.WAITING) {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
        this.status = ReservationStatus.CANCELLED_BY_SHOP;
    }
}
