package com.florinsutu.gestioneprenotazioni.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneprenotazioni.GestionePrenotazioniApplication;
import com.florinsutu.gestioneprenotazioni.entities.Reservation;
import com.florinsutu.gestioneprenotazioni.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository rr;
	
	Logger logger = LoggerFactory.getLogger(GestionePrenotazioniApplication.class);
	
	public void saveReservation(Reservation r) {
		for(Reservation res : getAllReservations()) {
			if(r.getDate().isEqual(res.getDate())) {
				logger.error("There is another reseravaion for this date");
				return;
			}
		}
		rr.save(r);
	}
	
	public void deleteReservationById(int id) {
		rr.deleteById(id);
	}
	
	public Optional<Reservation> getReservationById(int id) {
		return rr.findById(id);
	}
	
	public Iterable<Reservation> getAllReservations(){
		return rr.findAll();
	}
	
	public Page<Reservation> getAllAndPaginate(Pageable p){
		return rr.findAll(p);
	}
}
