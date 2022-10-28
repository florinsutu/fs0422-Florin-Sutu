package com.florinsutu.progetto_m2w1.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.florinsutu.progetto_m2w1.entities.Citta;
import com.florinsutu.progetto_m2w1.entities.Edificio;
import com.florinsutu.progetto_m2w1.entities.Indirizzo;
import com.florinsutu.progetto_m2w1.entities.Postazione;
import com.florinsutu.progetto_m2w1.entities.Prenotazione;
import com.florinsutu.progetto_m2w1.entities.Utente;

@Configuration
public class Config {

	@Bean
	@Scope("prototype")
	public Utente getUtente() {
		return new Utente();
	}
	
	@Bean
	@Scope("prototype")
	public Prenotazione getPrenotazione() {
		return Prenotazione.builder()
				.utente(getUtente())
				.postazione(getPostazione())
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Postazione getPostazione() {
		return new Postazione();
	}
	
	@Bean
	@Scope("prototype")
	public Edificio getEdificio() {
		return new Edificio();
	}
	
	@Bean
	@Scope("prototype")
	public Indirizzo getIndirizzo() {
		return new Indirizzo();
	}
	
	@Bean
	@Scope("prototype")
	public Citta getCitta() {
		return new Citta();
	}

}
