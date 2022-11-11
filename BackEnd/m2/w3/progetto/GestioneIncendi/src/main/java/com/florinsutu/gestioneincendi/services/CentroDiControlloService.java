package com.florinsutu.gestioneincendi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneincendi.exceptions.NotFoundException;
import com.florinsutu.gestioneincendi.models.CentroDiControllo;
import com.florinsutu.gestioneincendi.repositories.CentroDiControlloRepository;

@Service
public class CentroDiControlloService {
	
	@Autowired
	private CentroDiControlloRepository repository;

	public CentroDiControllo save(CentroDiControllo x) {
		return repository.save(x);
	}

	public List<CentroDiControllo> getAll() {
		return repository.findAll();
	}

	public CentroDiControllo getById(Long id) {
		
		Optional<CentroDiControllo> centroDiControllo = repository.findById(id);
		
		if(!centroDiControllo.isPresent())
			throw new NotFoundException("CentroDiControllo not available");
		
		return centroDiControllo.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}


//public CentroDiControllo getByName(String name) {
//	return repository.findByNameIgnoreCase(name);
//}