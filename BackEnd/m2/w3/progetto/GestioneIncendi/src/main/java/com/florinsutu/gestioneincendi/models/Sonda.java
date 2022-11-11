package com.florinsutu.gestioneincendi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.florinsutu.gestioneincendi.interfaces.SondaSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sonde")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Sonda implements SondaSubject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Rilevatore rilevatore;
	
	private String latitudine;
	private String longitudine;
	private int fumo;
	
	@Override
	public void register(Rilevatore r) {
		this.rilevatore = r;
		
	}
	@Override
	public void unregister() {
		this.rilevatore = null;
		
	}
	@Override
	public void notifyObservers() {
		if(fumo>5){
			rilevatore.update(latitudine, longitudine, fumo);
		}

		
	}
	@Override
	public Object getUpdate(Rilevatore r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String toString() {
		return String.format("id: %d,longitude: %s, latitude: %s, smoke:%d, rilevatoreId: %d", id, latitudine, longitudine, fumo, rilevatore.getId());
	}
	

}
