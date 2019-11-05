package com.teams810.reservation.api;

import com.teams810.reservation.api.entities.Reservation;
import com.teams810.reservation.api.exceptions.InvalidStatusFlowException;
import com.teams810.reservation.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ReservationApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApiApplication.class, args);
    }
}
