package com.teams810.reservation.api.controllers;

import com.teams810.reservation.api.discovery.ServiceDiscoveryClient;
import com.teams810.reservation.api.entities.Reservation;
import com.teams810.reservation.api.entities.service.Item;
import com.teams810.reservation.api.entities.service.User;
import com.teams810.reservation.api.exceptions.InvalidStatusFlowException;
import com.teams810.reservation.api.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ServiceDiscoveryClient discovery;

    @RequestMapping(value = "/")
    public String index() {
        return "Reservation Service";
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
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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

    @RequestMapping(value = "/reservation/id/{id}", method = RequestMethod.POST)
    public ResponseEntity<Reservation> getReservation(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get a reservation by id.
        // Returns: A certain reservation
        try {
            Reservation reservation = repository.findById(id);
            String userId = discovery.verifyUser(token).getUsername();
            String role = discovery.verifyUser(token).getRole();

            if (role.equals("customer") && reservation.getUserId().equals(userId)) {
                return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
            } else if (role.equals("shop") && checkIfIsReservationOfShop(reservation, token)) {
                return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException | HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reservation/me", method = RequestMethod.POST)
    public ResponseEntity<List<Reservation>> userReservation(
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list
        List<Reservation> reservationList = new ArrayList<Reservation>();
        User user = discovery.verifyUser(token);

        try {
            if (user.getRole().equals("customer")) {
                for (Reservation r : repository.findAll()) {
                    if (r.getUserId().equals(user.getUsername())) {
                        reservationList.add(r);
                    }
                }
            } else if (user.getRole().equals("shop")) {
                for (Reservation r : repository.findAll()) {
                    if (checkIfIsReservationOfShop(r, token)) {
                        reservationList.add(r);
                    }
                }
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reservation/id/{id}/cancel", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancelReservation(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation
        Reservation reservation = repository.findById(id);
        User user = discovery.verifyUser(token);

        try {
            String userId = user.getUsername();
            String role = user.getRole();

            if (role.equals("customer") && reservation.getUserId().equals(userId)) {
                reservation.cancelByUser();
            } else if (role.equals("shop") && checkIfIsReservationOfShop(reservation, token)) {
                try {
                    reservation.checkMissed();
                } catch (InvalidStatusFlowException e) {
                    reservation.cancelByShop();
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            repository.save(reservation);

            return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
        } catch (NullPointerException | HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidStatusFlowException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reservation/id/{id}/check", method = RequestMethod.POST)
    public ResponseEntity<Reservation> checkReservation(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        // Operation: Checks a reservation, changes reservation status.
        // Returns: Checked Reservation
        Reservation reservation = repository.findById(id);
        String role = discovery.verifyUser(token).getRole();

        try {
            if (role.equals("shop") && checkIfIsReservationOfShop(reservation, token)) {
                reservation.checkSuccess();
            } else {
                return null;
            }

            repository.save(reservation);

            return new ResponseEntity<Reservation>(repository.findById(id), HttpStatus.OK);
        } catch (NullPointerException | HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidStatusFlowException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkIfIsReservationOfShop(Reservation reservation, String token) {
        // Operation: Check if a reservation is a reservation of a certain shop by shop token
        // Returns: boolean
        for (Object i : discovery.getShopItems(token).getItems()) {
            if (reservation.getItems().get(0).getItemId().equals(((Item) i).get_id())) {
                return true;
            }
        }
        return false;
    }
}
