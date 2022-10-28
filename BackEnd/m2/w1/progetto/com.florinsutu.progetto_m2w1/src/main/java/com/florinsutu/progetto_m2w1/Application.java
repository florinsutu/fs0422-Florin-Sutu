package com.florinsutu.progetto_m2w1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.florinsutu.progetto_m2w1.configs.Config;
import com.florinsutu.progetto_m2w1.entities.Prenotazione;
import com.florinsutu.progetto_m2w1.services.PrenotazioneService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	PrenotazioneService s;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		System.out.println("prova2");
		Prenotazione p = config();
		//s.savePrenotazione(p);
		
	}
	
	public static Prenotazione config() {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		Prenotazione p = ctx.getBean("getPrenotazione",Prenotazione.class);
		
		ctx.close();
		return p;
		
//		Car lamborghini = ctx.getBean("getPetrolCar", Car.class);
//		Car bmw = ctx.getBean("getDieselCar", Car.class);
//		Car tesla = ctx.getBean("getElectricCar", Car.class);
//		
//		((AnnotationConfigApplicationContext) ctx).close();
//		
//		return new Car[] {lamborghini, bmw, tesla};
	}

}
