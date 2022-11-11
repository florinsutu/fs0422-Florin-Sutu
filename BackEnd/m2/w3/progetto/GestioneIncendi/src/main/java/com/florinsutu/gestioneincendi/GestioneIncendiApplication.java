package com.florinsutu.gestioneincendi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.florinsutu.gestioneincendi.models.CentroDiControllo;
import com.florinsutu.gestioneincendi.models.Rilevatore;
import com.florinsutu.gestioneincendi.models.Sonda;

@SpringBootApplication
public class GestioneIncendiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneIncendiApplication.class, args);
		
		CentroDiControllo c = new CentroDiControllo();
		
		Rilevatore r = new Rilevatore();
		
		Sonda s = Sonda.builder()
				.latitudine("60°")
				.longitudine("40°")
				.fumo(7)
				.build();
		
		s.register(r);
		r.register(c);
		
		r.addSonda(s);
		c.addRilevatore(r);
				
		s.notifyObservers();
		System.out.println(c);
	}

}
