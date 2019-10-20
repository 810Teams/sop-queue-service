package main;

import core.TimePeriod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReservationService {
    public static void main(String[] args) {
        SpringApplication.run(ReservationService.class, args);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public static void createReservation() {
        //TODO: Implements reservation creation
    }

    @RequestMapping("/cancel/{id}")
    public static void cancelReservation(@PathVariable String id) {
        //TODO: Implements reservation creation
    }
}
