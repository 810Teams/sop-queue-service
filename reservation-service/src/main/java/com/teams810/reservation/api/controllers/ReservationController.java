package com.teams810.reservation.api.controllers;

import com.teams810.reservation.api.discovery.ServiceDiscoveryClient;
import com.teams810.reservation.api.entities.Reservation;
import com.teams810.reservation.api.entities.TimePeriod;
import com.teams810.reservation.api.entities.service.Item;
import com.teams810.reservation.api.entities.service.Shop;
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
            // UNAUTHORIZED
            return null;
        }
    }

    @RequestMapping(value = "/reservation/all", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getAllReservation() {
        // Operation: Get all reservations
        // Returns: All reservations
        // TODO: Once deployed, remove this method. This method is only for development.
        List<Reservation> reservationList = new ArrayList<Reservation>();

        for (Reservation r : repository.findAll()) {
            reservationList.add(r);
        }

        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/view/{id}", method = RequestMethod.POST)
    public ResponseEntity<Reservation> getReservation(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get a reservation by id.
        // Returns: A certain reservation
        try {
            String userId = discovery.verifyUser(token).getUsername();

            if (repository.findById(id).getUserId().equals(userId)) {
                return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
            } else {
                // UNAUTHORIZED
                return null;
            }
        } catch (NullPointerException | HttpClientErrorException e) {
            // NOT_FOUND
            return null;
        }
    }

    @RequestMapping(value = "/reservation/user/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<Reservation>> userReservation(
            @PathVariable("userId") String userId,
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
                // UNAUTHORIZED
                return null;
            }
        } catch (HttpClientErrorException e) {
            // NOT_FOUND
            return null;
        }
    }

    @RequestMapping(value = "/reservation/shop/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<Reservation>> shopReservation(
            @PathVariable("userId") String userId,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list of a cart
        List<Reservation> reservationList = new ArrayList<Reservation>();

        try {
            if (userId.equals(discovery.verifyUser(token).getUsername())) {
                for (Reservation r : repository.findAll()) {
                    for (Object i : discovery.getShopItems(token).getItems()) {
                        if (r.getItemData().get(0).getItemId().equals(((Item)i).get_id())) {
                            reservationList.add(r);
                            break;
                        }
                    }
                }
                return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
            } else {
                // UNAUTHORIZED
                return null;
            }
        } catch (HttpClientErrorException e) {
            // NOT_FOUND
            return null;
        }
    }

    @RequestMapping(value = "/reservation/cancel/{id}", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancelReservation(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation
        Reservation reservation = repository.findById(id);

        try {
            String userId = discovery.verifyUser(token).getUsername();
            String role = discovery.verifyUser(token).getRole();

            if (role.equals("customer")) {
                if (reservation.getUserId().equals(userId)) {
                    reservation.cancelByUser();
                } else {
                    // UNAUTHORIZED
                    return null;
                }
            } else if (role.equals("shop")) {
                if (userId.equals(discovery.getShop(token).getOwner())) {
                    reservation.cancelByShop();
                } else {
                    // UNAUTHORIZED
                    return null;
                }
            }

            repository.save(reservation);

            return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
        } catch (NullPointerException | HttpClientErrorException | InvalidStatusFlowException e) {
            // NOT_FOUND | NOT_FOUND | INTERNAL_SERVER_ERROR
            return null;
        }
    }
}
