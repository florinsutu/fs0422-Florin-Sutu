package com.florinsutu.gestioneprenotazioni.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.florinsutu.gestioneprenotazioni.GestionePrenotazioniApplication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	String username;
	String fullName;
	String email;
	String password;
	Boolean active;

	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "user")
	Set<Reservation> reservations;
	
	public void addReservation(Reservation r) {
		Logger logger = LoggerFactory.getLogger(GestionePrenotazioniApplication.class);
		if(reservations == null) reservations = new HashSet<Reservation>();
		
		if(reservations.add(r)) {
			reservations.add(r);
			logger.info("Reservation added successfully");
		} else {
			logger.error("Reservation is already present");
		}
	}
}
