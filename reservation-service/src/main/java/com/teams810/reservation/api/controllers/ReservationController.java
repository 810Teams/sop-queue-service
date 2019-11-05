package com.teams810.reservation.api.controllers;

import com.teams810.reservation.api.entities.Reservation;
import com.teams810.reservation.api.entities.TimePeriod;
import com.teams810.reservation.api.exceptions.InvalidStatusFlowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ReservationController {
    private List<Reservation> reservationList = new ArrayList<Reservation>(
            Arrays.asList(
                    new Reservation("1", "810teams", "chicken_rice", new TimePeriod("2019-11-06 12:00", "2019-11-06 12:15")),
                    new Reservation("2", "810teams", "ramen", new TimePeriod("2019-11-07 12:00", "2019-11-07 12:15"))
            )
    );

    @RequestMapping(value = "/")
    public String index() {
        // Type: -
        // Operation: Shows com.teams810.reservation.api.service welcome text.
        // Returns: Welcome text as a string

        return "Reservation Service Index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Reservation> newReservation(
            @RequestBody Reservation reservation
    ) {
        // Type: CREATE
        // Operation: Creates a reservation from POST request.
        // Returns: Reservation created

        reservationList.add(reservation);

        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getAllReservation() {
        // Type: GET
        // Operation: Get all reservations
        // Returns: All reservations

        return new ResponseEntity<List<Reservation>>(this.reservationList, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservation(
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

    @RequestMapping(value = "/user/{userId}/reservation", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> userReservation(
            @PathVariable String userId
    ) {
        // Type: GET
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list of a cart

        ArrayList<Reservation> myReservationList = new ArrayList<Reservation>();

        for (Reservation r : this.reservationList) {
            if (r.getUserId().equals(userId)) {
                myReservationList.add(r);
            }
        }

        return new ResponseEntity<List<Reservation>>(myReservationList, HttpStatus.OK);
    }

    // TODO: Implements shop reservation list

    @RequestMapping(value = "/reservation/{id}/cancel", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancelReservation(
            @PathVariable String id
    ) {
        // Type: DELETE
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation

        // Notes: Reservation can be cancelled by both customer and shop owner

        Reservation reservation = null;

        for (Reservation r : this.reservationList) {
            if (r.getId().equals(id)) {
                reservation = r;
            }
        }

        try {
            reservation.cancelByUser();
        } catch (InvalidStatusFlowException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            return null;
        }

        // TODO: Implements cancel by shop


        return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
    }
}
