package com.teams810.reservation.api.repositories;

import com.teams810.reservation.api.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Reservation findById(Long id);
}
