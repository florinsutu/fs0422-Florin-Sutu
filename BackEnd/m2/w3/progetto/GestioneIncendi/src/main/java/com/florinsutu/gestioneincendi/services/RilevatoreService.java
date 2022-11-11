package com.florinsutu.gestioneincendi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneincendi.exceptions.NotFoundException;
import com.florinsutu.gestioneincendi.models.Rilevatore;
import com.florinsutu.gestioneincendi.repositories.RilevatoreRepository;

@Service
public class RilevatoreService {
	
	@Autowired
	private RilevatoreRepository repository;

	public Rilevatore save(Rilevatore x) {
		return repository.save(x);
	}

	public List<Rilevatore> getAll() {
		return repository.findAll();
	}

	public Rilevatore getById(Long id) {
		
		Optional<Rilevatore> rilevatore = repository.findById(id);
		
		if(!rilevatore.isPresent())
			throw new NotFoundException("Rilevatore not available");
		
		return rilevatore.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
