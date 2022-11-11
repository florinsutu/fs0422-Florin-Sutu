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

import com.florinsutu.gestioneincendi.models.CentroDiControllo;
import com.florinsutu.gestioneincendi.services.CentroDiControlloService;

@RestController
@RequestMapping("/api/centers/") //TODO impostare la rotta
public class CentroDiControlloController {

	private final Logger logger = LoggerFactory.getLogger(CentroDiControlloController.class);

	@Autowired
	private CentroDiControlloService centroDiControlloService;

	@PostMapping
	public CentroDiControllo saveCentroDiControllo(
//          TODO gestire il post			
//			@Valid
//			@RequestParam("name") String name,
//			@RequestParam(value="address",required=false) String address,
	) {
		CentroDiControllo centroDiControllo = CentroDiControllo.builder().build();

		logger.info("Save CentroDiControllo in XController");
		return centroDiControlloService.save(centroDiControllo);
	}

	@GetMapping
	public List<CentroDiControllo> getCentroDiControlloList() {
		return centroDiControlloService.getAll();
	}

	@GetMapping("{id}")
	public CentroDiControllo getCentroDiControlloById(@PathVariable("id") Long id) {
		return centroDiControlloService.getById(id);
	}

	@DeleteMapping("{id}")
	public String deleteCentroDiControlloById(@PathVariable("id") Long id) {
		centroDiControlloService.deleteById(id);
		return "CentroDiControllo deleted successfully";
	}

	@PutMapping("{id}")
	public CentroDiControllo updateCentroDiControllo(
			@PathVariable("id") Long id
//			@RequestParam("name") String name
			
			) {

		CentroDiControllo centroDiControllo = centroDiControlloService.getById(id);

		//TODO gestire il put
		
		centroDiControlloService.save(centroDiControllo);
		return centroDiControllo;
	}

}




//@GetMapping("name/{name}")
//public CentroDiControllo getCentroDiControlloByName(@PathVariable("name") String name) {
//	return service.getByName(name);
//}
