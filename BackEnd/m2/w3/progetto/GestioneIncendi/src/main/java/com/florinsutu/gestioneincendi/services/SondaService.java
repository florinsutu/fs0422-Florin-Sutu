package com.florinsutu.gestioneincendi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneincendi.exceptions.NotFoundException;
import com.florinsutu.gestioneincendi.models.Sonda;
import com.florinsutu.gestioneincendi.repositories.SondaRepository;

@Service
public class SondaService {
	
	@Autowired
	private SondaRepository repository;

	public Sonda save(Sonda x) {
		return repository.save(x);
	}

	public List<Sonda> getAll() {
		return repository.findAll();
	}

	public Sonda getById(Long id) {
		
		Optional<Sonda> sonda = repository.findById(id);
		
		if(!sonda.isPresent())
			throw new NotFoundException("Sonda not available");
		
		return sonda.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
