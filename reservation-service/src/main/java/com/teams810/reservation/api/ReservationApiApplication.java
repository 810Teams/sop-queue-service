package com.teams810.reservation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ReservationApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApiApplication.class, args);
    }
}
