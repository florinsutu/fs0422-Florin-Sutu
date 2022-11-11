package com.florinsutu.gestioneincendi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestioneincendi.models.Sonda;
import com.florinsutu.gestioneincendi.services.SondaService;

@RestController
@RequestMapping("/api/sonde/") //TODO impostare la rotta
public class SondaController {

	private final Logger logger = LoggerFactory.getLogger(SondaController.class);

	@Autowired
	private SondaService sondaService;

	@PostMapping
	public Sonda saveSonda(
//          TODO gestire il post			
//			@Valid
//			@RequestParam("name") String name,
//			@RequestParam(value="address",required=false) String address,
	) {
		Sonda sonda = Sonda.builder().build();

		logger.info("Save Sonda in XController");
		return sondaService.save(sonda);
	}

	@GetMapping
	public List<Sonda> getSondaList() {
		return sondaService.getAll();
	}

	@GetMapping("{id}")
	public Sonda getSondaById(@PathVariable("id") Long id) {
		return sondaService.getById(id);
	}

	@DeleteMapping("{id}")
	public String deleteSondaById(@PathVariable("id") Long id) {
		sondaService.deleteById(id);
		return "Sonda deleted successfully";
	}

	@PutMapping("{id}")
	public Sonda updateSonda(
			@PathVariable("id") Long id
//			@RequestParam("name") String name
			) {

		Sonda sonda = sondaService.getById(id);

		//TODO gestire il put
		
		sondaService.save(sonda);
		return sonda;
	}

}




//@GetMapping("name/{name}")
//public Sonda getSondaByName(@PathVariable("name") String name) {
//	return service.getByName(name);
//}
