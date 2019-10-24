package main;

import core.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import token.AccountTypeToken;
import token.ItemToken;
import token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
@RestController
public class ReservationService {
    private ArrayList<Reservation> reservationList;

    public static void main(String[] args) {
        SpringApplication.run(ReservationService.class, args);
    }

    public ReservationService() {
        reservationList = new ArrayList<Reservation>();
    }

    public ReservationService(ArrayList<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @RequestMapping(value = "/")
    public String index() {
        // Type: -
        // Operation: Shows service welcome text.
        // Returns: Welcome text as a string

        return "Reservation Service Index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Reservation> create(
            @RequestBody Reservation reservation
    ) {
        // Type: CREATE
        // Operation: Creates a reservation from POST request.
        // Returns: Reservation created

        reservationList.add(reservation);

        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> get(
            @PathVariable String id
    ) {
        // Type: GET
        // Operation: Get a reservation by id.
        // Returns: A certain reservation

        Reservation reservation = null;

        for (Reservation r : this.reservationList) {
            if (r.getItemId().equals(id)) {
                reservation = r;
            }
        }

        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<Reservation>> list(
            @PathVariable String userId
            ) {
        // Type: GET
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list of a cart

        ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

        for (Reservation r : this.reservationList) {
            if (r.getUserId().equals(userId)) {
                reservationList.add(r);
            }
        }

        // TODO: Implements shop owner received reservation list

        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}/cancel", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancel(
            @PathVariable String id
    ) {
        // Type: DELETE
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation

        // Notes: Reservation can be cancelled by both customer and shop owner

        Reservation reservation = null;

        for (Reservation r : this.reservationList) {
            if (r.getItemId().equals(id)) {
                reservation = r;
            }
        }

        return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
    }
}
