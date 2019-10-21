package main;

import core.Reservation;
import core.TimePeriod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ReservationService {
    public static void main(String[] args) {
        SpringApplication.run(ReservationService.class, args);
    }

    @RequestMapping(value = "/")
    public String index() {
        // Type: -
        // Operation: Shows service welcome text.
        // Returns: Welcome text as a string
        return "Reservation Service Index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Reservation> create() {
        // Type: CREATE
        // Operation: Creates a reservation from POST request.
        // Returns: Reservation created

        //TODO: Implements reservation creation

        return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> get(@PathVariable String id) {
        // Type: GET
        // Operation: Get a reservation by id.
        // Returns: A certain reservation

        //TODO: Implements reservation creation

        return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> list(@PathVariable String userId) {
        // Type: GET
        // Operation: Get all reservations of a certain user.
        // Returns: Reservation list of a certain user

        // TODO: Implements reservation list

        return new ResponseEntity<List<Reservation>>(new ArrayList<Reservation>(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}/cancel", method = RequestMethod.POST)
    public ResponseEntity<Reservation> cancel(@PathVariable String id) {
        // Type: DELETE
        // Operation: Cancel a reservation, changes reservation status.
        // Returns: Cancelled reservation

        // Notes: Reservation can be cancelled by both customer and shop owner

        //TODO: Implements reservation creation

        return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
    }
}
