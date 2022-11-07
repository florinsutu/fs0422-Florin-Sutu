package com.florinsutu.gestioneprenotazioni.controllers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestioneprenotazioni.entities.Building;
import com.florinsutu.gestioneprenotazioni.entities.Reservation;
import com.florinsutu.gestioneprenotazioni.entities.User;
import com.florinsutu.gestioneprenotazioni.entities.Workstation;
import com.florinsutu.gestioneprenotazioni.services.ReservationService;
import com.florinsutu.gestioneprenotazioni.services.UserService;
import com.florinsutu.gestioneprenotazioni.services.WorkstationService;

@RestController
@RequestMapping("/api")
public class ReservationControl {

	@Autowired
	ReservationService rs;
	
	@Autowired
	UserService us;
	
	@Autowired
	WorkstationService ws;
	
	//--------------- GET -------------------
	
	@GetMapping("/reservations/{id}")
	public Optional<Reservation> getReservationById(@PathVariable("id") int id) {
		return rs.getReservationById(id);
	}

	@GetMapping("/reservations")
	public Iterable<Reservation> getAllReservations(){
		return rs.getAllReservations();
	}

	@GetMapping("/reservations-paginate")
	public ResponseEntity<Page<Reservation>> getAllReservationsAndPaginate(Pageable p){
		Page<Reservation> res = rs.getAllAndPaginate(p);
		if (res.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else{
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	//--------------- POST -------------------
	
	@PostMapping("/reservations")
	public Reservation postReservation(
			@RequestParam("date") LocalDate date,
			@RequestParam("user_id") int user_id,
			@RequestParam("workstation_id") int workstation_id
	) {
		Iterable<Reservation> reservations = getAllReservations();
		User u = User.builder()
				.email(randomString())
				.fullName(randomString())
				.username(randomString())
				.password("test")
				.build();
		Workstation w = Workstation.builder()
				.description(randomString())
				.building(Building.builder().name(randomString()).address(randomString()).city(randomString()).build())
				.build();

		for(Reservation r : reservations){
			if(r.getDate().isEqual(date)) return null;
		}
			Reservation res = Reservation.builder()
						.date(date)
						.user(u)
						.workstation(w)
						.build();
			us.saveUser(u);
			ws.saveWorkstation(w);
			rs.saveReservation(res);
			return res;
	}

	private String randomString(){
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = ThreadLocalRandom.current().nextInt(4, 9);
		Random random = new Random();

		String randomString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return randomString.substring(0,1) + randomString.substring(1);
	}
	//--------------- PUT -------------------
	
	@PutMapping("/reservations/{id}")
	public Reservation putReservation(
			@PathVariable("id") int id,
			@RequestParam(name="date", required = false) LocalDate date,
			@RequestParam(name="user_id", required = false) Integer user_id,
			@RequestParam(name="workstation_id", required = false) Integer workstation_id
	) {
		
		Iterable<Reservation> reservations = getAllReservations();
		Optional<Reservation> r = getReservationById(id);
		
		for(Reservation re : reservations){
			if(re.getDate().isEqual(date)) return null;
		}
		
		if(r.isPresent()) {
			Reservation res = r.get();
			if(date != null) res.setDate(date);
			if(user_id != null) {
				Optional<User> u = us.getUserById(user_id);
				if(u.isPresent()) res.setUser(u.get());
			}
			if(workstation_id != null) {
				Optional<Workstation> w = ws.getWorkstationById(workstation_id);
				if(w.isPresent()) res.setWorkstation(w.get());
			}
			rs.saveReservation(res);
		}
		return null;
	}
	//--------------- DELETE -------------------
	@DeleteMapping("/reservations/{id}")
	public Reservation deleteReservation(@PathVariable("id") int id) {
		
		Optional<Reservation> r = getReservationById(id);
		if(r.isPresent()) {
			rs.deleteReservationById(id);
			return r.get();
		}
		return null;
	}
}
