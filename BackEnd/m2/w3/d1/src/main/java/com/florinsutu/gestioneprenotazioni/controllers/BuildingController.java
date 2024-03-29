package com.florinsutu.gestioneprenotazioni.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestioneprenotazioni.entities.Building;
import com.florinsutu.gestioneprenotazioni.entities.Workstation;
import com.florinsutu.gestioneprenotazioni.services.BuildingService;
import com.florinsutu.gestioneprenotazioni.services.WorkstationService;

@RestController
@RequestMapping("/api")
public class BuildingController {

	@Autowired
	BuildingService bs;
	
	@Autowired
	WorkstationService ws;
	
	//--------------- GET -------------------
	
	@GetMapping("/buildings/{id}")
	public Optional<Building> getBuildingById(@PathVariable("id") int id) {
		return bs.getBuildingById(id);
	}
	
	@GetMapping("/buildings")
	public Iterable<Building> getAllBuildings(){
		return bs.getAllBuildings();
	}

	@GetMapping("/buildings-paginate")
	public ResponseEntity<Page<Building>> getAllBuildingsAndPaginate(Pageable p){
		Page<Building> res = bs.getAllAndPaginate(p);
		if (res.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else{
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	//--------------- POST -------------------
	
	@PostMapping("/buildings")
	public Building postBuilding(
			@Valid
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("city") String city,
			@RequestParam("security_code") String code
	) {
		Building b = Building.builder()
				.name(name)
				.address(address)
				.city(city)
				.securityCode(code)
				.build();
		
		
		bs.saveBuilding(b);
		
		return b;
	}
	
	//--------------- PUT -------------------
	
	@PutMapping("/buildings/{id}")
	public Building putBuilding(
			@PathVariable("id") int id,
			@RequestParam(name="name", required = false) String name,
			@RequestParam(name="address", required = false) String address,
			@RequestParam(name="city", required = false) String city
	) {
		Optional<Building> b = getBuildingById(id);
		
		if(b.isPresent()) {
			Building building = b.get();
			if(name != null) building.setName(name);
			if(address != null) building.setAddress(address);
			if(city != null) building.setCity(city);
			
			bs.saveBuilding(building);
			return building;
		}
		return null;
	}
	//--------------- DELETE -------------------
	
	@DeleteMapping("/buildings/{id}")
	public Optional<Building> deleteBuildingById(@PathVariable("id") int id) {
		
		Optional<Building> b = getBuildingById(id);
		
		if(b.isPresent()) {
			bs.deleteBuildingById(id);
			return bs.getBuildingById(id);
		}
		return null;
		
	}
	
}
