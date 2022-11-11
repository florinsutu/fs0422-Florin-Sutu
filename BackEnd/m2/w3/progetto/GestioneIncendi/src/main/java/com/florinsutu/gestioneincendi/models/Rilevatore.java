package com.florinsutu.gestioneincendi.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.florinsutu.gestioneincendi.interfaces.RilevatoreObserver;
import com.florinsutu.gestioneincendi.interfaces.RilevatoreSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rilevatori")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rilevatore implements RilevatoreSubject, RilevatoreObserver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private Set<Sonda> sonde = new HashSet<Sonda>();
	
	@ManyToOne
	private CentroDiControllo centro;

	public void update(String latitudine, String longitudine, int fumo ) {
		
		this.notifyObservers(latitudine,longitudine,fumo);
	
	}
	
	@Override
	public void addSonda(Sonda sonda) {
		this.sonde.add(sonda);
	}

	@Override
	public void register(CentroDiControllo r) {
		this.centro = r;
		
	}

	@Override
	public void unregister() {
		this.centro = null;
		
	}

	@Override
	public void notifyObservers(String latitudine, String longitudine, int fumo) {
		
		centro.update(latitudine,longitudine,fumo);
	}

	@Override
	public Object getUpdate(CentroDiControllo r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d,sonde: %s, centerId: %d", id, sonde, centro.getId());
	}

	

}
