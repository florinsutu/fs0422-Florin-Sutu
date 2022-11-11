package com.florinsutu.gestioneincendi.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.florinsutu.gestioneincendi.interfaces.CentroDiControlloObserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "centri_di_controllo")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CentroDiControllo implements CentroDiControlloObserver{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private Set<Rilevatore> rilevatori = new HashSet<>();
	
	@Override
	public void addRilevatore(Rilevatore r) {
		rilevatori.add(r);
	}

	@Override
	public void update(String latitudine, String longitudine, int fumo) {
	
		System.out.println(latitudine + longitudine + fumo);
		
	}

	@Override
	public String toString() {
		return String.format("id: %d, rilevatori: %s",id, rilevatori);
	}
	
}
