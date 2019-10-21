package core;

import exceptions.InvalidStatusFlowException;

import java.time.LocalDateTime;

public class Reservation {
    enum Status {
        WAITING,    // Good Case: Customer before receiving reserved goods or services.
        SUCCESS,    // Good Case: Customer received goods or services successfully.
        MISSED,     // Bad Case:  Customer did not show up on time.
        CANCELLED_BY_USER,  // Bad Case:  Customer cancelled the reservation.
        CANCELLED_BY_SHOP   // Bad Case:  Shop owner cancelled customer's reservation (for some reasons).
    }

    private String userId;
    private String itemId;
    private int amount;
    private TimePeriod timePeriod;
    private Status status;

    public Reservation() {
    }

    public Reservation(String userId, String itemId, int amount, TimePeriod timePeriod) {
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.status = Status.WAITING;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) throws InvalidStatusFlowException {
        if (status == this.status) {
            throw new InvalidStatusFlowException("Unchanged status flow.");
        } else if (status == Status.WAITING) {
            this.setStatusWaiting();
        } else if (status == Status.SUCCESS) {
            this.setStatusSuccess();
        } else if (status == Status.CANCELLED_BY_USER) {
            this.setStatusCancelledByUser();
        } else if (status == Status.MISSED) {
            this.setStatusMissed();
        } else if (status == Status.CANCELLED_BY_SHOP) {
            this.setStatusCancelledByShop();
        }
    }

    private void setStatusWaiting() throws InvalidStatusFlowException {
        throw new InvalidStatusFlowException("Unable to revert status flow");
    }

    private void setStatusSuccess() throws InvalidStatusFlowException {
        if (this.status == Status.WAITING) {
            this.status = Status.SUCCESS;
        } else {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
    }

    private void setStatusMissed() throws InvalidStatusFlowException {
        if (this.status == Status.WAITING && LocalDateTime.now().compareTo(this.timePeriod.getEndDateTime()) > 0) {
            this.status = Status.MISSED;
        } else {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
    }

    private void setStatusCancelledByUser() throws InvalidStatusFlowException {
        if (this.status == Status.WAITING && LocalDateTime.now().compareTo(this.timePeriod.getStartDateTime()) < 0) {
            this.status = Status.CANCELLED_BY_USER;
        } else {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
    }

    private void setStatusCancelledByShop() throws InvalidStatusFlowException {
        if (this.status == Status.WAITING) {
            this.status = Status.CANCELLED_BY_SHOP;
        } else {
            throw new InvalidStatusFlowException("Invalid status flow.");
        }
    }
}
