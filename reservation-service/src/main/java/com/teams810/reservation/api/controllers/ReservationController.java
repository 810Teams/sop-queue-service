package com.teams810.reservation.api.controllers;

import com.teams810.reservation.api.discovery.ServiceDiscoveryClient;
import com.teams810.reservation.api.entities.Reservation;
import com.teams810.reservation.api.entities.TimePeriod;
import com.teams810.reservation.api.exceptions.InvalidStatusFlowException;
import com.teams810.reservation.api.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ServiceDiscoveryClient discovery;

    @RequestMapping(value = "/")
    public String index() {
        // Operation: Shows com.teams810.reservation.api.service welcome text.
        // Returns: Welcome text as a string

        return "Reservation Service Index";
    }

    @RequestMapping(value = "/reservation/new", method = RequestMethod.POST)
    public ResponseEntity<Reservation> newReservation(
            @RequestBody Reservation reservation,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Creates a reservation from POST request.
        // Returns: Reservation created
        try {
            reservation.setUserId(discovery.verifyUser(token).getUsername());
            repository.save(reservation);

            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            // TODO: Make sure this returns UNAUTHORIZED
            return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/reservation/all", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getAllReservation() {
        // Operation: Get all reservations
        // Returns: All reservations
        List<Reservation> reservationList = new ArrayList<Reservation>();

        for (Reservation r : repository.findAll()) {
            reservationList.add(r);
        }

        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservation(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get a reservation by id.
        // Returns: A certain reservation
        try {
            String userId = discovery.verifyUser(token).getUsername();

            if (repository.findById(id).getUserId().equals(userId)) {
                return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
            } else {
                // TODO: Make sure this returns UNAUTHORIZED
                return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException | HttpClientErrorException e) {
            // TODO: Make sure this returns NOT_FOUND
            return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reservation/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> userReservation(
            @PathVariable String userId,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list of a cart

        List<Reservation> reservationList = new ArrayList<Reservation>();

        try {
            if (userId.equals(discovery.verifyUser(token).getUsername())) {
                for (Reservation r : repository.findAll()) {
                    if (r.getUserId().equals(userId)) {
                        reservationList.add(r);
                    }
                }
                return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
            } else {
                // TODO: Make sure this returns UNAUTHORIZED
                return new ResponseEntity<List<Reservation>>(new ArrayList<Reservation>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (HttpClientErrorException e) {
            // TODO: Make sure this returns NOT_FOUND
            return new ResponseEntity<List<Reservation>>(new ArrayList<Reservation>(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO: Implements shop reservation list

    @RequestMapping(value = "/reservation/id/{id}/cancel", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancelReservation(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation

        Reservation reservation = repository.findById(id);

        try {


        }  catch (NullPointerException ex) {
            return null;
        }

        try {
            String userId = discovery.verifyUser(token).getUsername();
            String role = discovery.verifyUser(token).getRole();

            if (role.equals("customer")) {
                if (reservation.getUserId().equals(userId)) {
                    reservation.cancelByUser();
                } else {
                    // TODO: Make sure this returns UNAUTHORIZED
                    return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.UNAUTHORIZED);
                }
            } else if (role.equals("shop")) {
                // TODO: Implements cancel by shop
            }

            repository.save(reservation);
            return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
        } catch (NullPointerException | HttpClientErrorException e) {
            // TODO: Make sure this returns NOT_FOUND
            return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.NOT_FOUND);
        } catch (InvalidStatusFlowException ex) {
            // TODO: Make sure this returns INTERNAL_SERVER_ERROR
            return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
