package core;

public enum ReservationStatus {
    WAITING,    // Good Case: Customer before receiving reserved goods or services.
    SUCCESS,    // Good Case: Customer received goods or services successfully.
    MISSED,     // Bad Case:  Customer did not show up on time.
    CANCELLED_BY_USER,  // Bad Case:  Customer cancelled the reservation.
    CANCELLED_BY_SHOP   // Bad Case:  Shop owner cancelled customer's reservation (for some reasons).
}
