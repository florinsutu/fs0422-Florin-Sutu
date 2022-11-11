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

import com.florinsutu.gestioneincendi.models.Rilevatore;
import com.florinsutu.gestioneincendi.services.RilevatoreService;
@RestController
@RequestMapping("/api/rilevatori/") //TODO impostare la rotta
public class RilevatoreController {

	private final Logger logger = LoggerFactory.getLogger(RilevatoreController.class);

	@Autowired
	private RilevatoreService rilevatoreService;

	@PostMapping
	public Rilevatore saveRilevatore(
//          TODO gestire il post			
//			@Valid
//			@RequestParam("name") String name,
//			@RequestParam(value="address",required=false) String address,
	) {
		Rilevatore rilevatore = Rilevatore.builder().build();

		logger.info("Save Rilevatore in XController");
		return rilevatoreService.save(rilevatore);
	}

	@GetMapping
	public List<Rilevatore> getRilevatoreList() {
		return rilevatoreService.getAll();
	}

	@GetMapping("{id}")
	public Rilevatore getRilevatoreById(@PathVariable("id") Long id) {
		return rilevatoreService.getById(id);
	}

	@DeleteMapping("{id}")
	public String deleteRilevatoreById(@PathVariable("id") Long id) {
		rilevatoreService.deleteById(id);
		return "Rilevatore deleted successfully";
	}

	@PutMapping("{id}")
	public Rilevatore updateRilevatore(
			@PathVariable("id") Long id
//			@RequestParam("name") String name
			) {

		Rilevatore rilevatore = rilevatoreService.getById(id);

		//TODO gestire il put
		
		rilevatoreService.save(rilevatore);
		return rilevatore;
	}

}




//@GetMapping("name/{name}")
//public Rilevatore getRilevatoreByName(@PathVariable("name") String name) {
//	return service.getByName(name);
//}
