package com.florinsutu.gestioneprenotazioni.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneprenotazioni.entities.Workstation;
import com.florinsutu.gestioneprenotazioni.entities.WorkstationType;
import com.florinsutu.gestioneprenotazioni.repositories.WorkstationRepository;

@Service
public class WorkstationService {

	@Autowired
	WorkstationRepository wr;
	
	@Autowired
	BuildingService bs;
	
	public void saveWorkstation(Workstation w) {
		wr.save(w);
	}
	
	public void deleteWorkstationById(int id) {
		wr.deleteById(id);
	}
	
	public Optional<Workstation> getWorkstationById(int id) {
		return wr.findById(id);
	}
	
	public Iterable<Workstation> getAllWorkstations(){
		return wr.findAll();
	}
	
	public Page<Workstation> getAllAndPaginate(Pageable p){
		return wr.findAll(p);
	}


	public Page<Workstation> getWorkstationByType(WorkstationType t, Pageable p){
		return wr.findWorkstationByWSType(t, p);
	}


	public Page<Workstation> getWorkstationsByCityAndType(String city, WorkstationType t, Pageable p){
		return wr.findWorkstationsByCityAndWSType(city, t, p);
	}
}
