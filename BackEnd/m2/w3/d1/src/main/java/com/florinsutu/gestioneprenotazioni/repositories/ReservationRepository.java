package com.florinsutu.gestioneprenotazioni.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.gestioneprenotazioni.entities.Reservation;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer>{

}
