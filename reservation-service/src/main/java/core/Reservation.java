package core;

import exceptions.InvalidStatusFlowException;

import java.time.LocalDateTime;

public class Reservation {
    private String userId;
    private String itemId;
    private int amount;
    private TimePeriod timePeriod;
    private ReservationStatus status;

    public Reservation() {
        // Constructor: Empty constructor
    }

    public Reservation(String userId, String itemId, TimePeriod timePeriod) {
        // Constructor: Single amount
        this(userId, itemId, 1, timePeriod);
    }

    public Reservation(String userId, String itemId, int amount, TimePeriod timePeriod) {
        // Constructor: Multiple amount
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.status = ReservationStatus.WAITING;
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
