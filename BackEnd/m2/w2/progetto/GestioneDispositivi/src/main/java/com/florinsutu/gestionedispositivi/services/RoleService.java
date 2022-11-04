package com.florinsutu.gestionedispositivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestionedispositivi.entities.Role;
import com.florinsutu.gestionedispositivi.exceptions.UserNotFoundException;
import com.florinsutu.gestionedispositivi.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public Role save(Role x) {
		return repository.save(x);
	}

	public List<Role> getAll() {
		return repository.findAll();
	}

	public Role getById(Long id) {
		
		Optional<Role> role = repository.findById(id);
		
		if(!role.isPresent())
			throw new UserNotFoundException("Role not available");
		
		return role.get();
	}
	
}
