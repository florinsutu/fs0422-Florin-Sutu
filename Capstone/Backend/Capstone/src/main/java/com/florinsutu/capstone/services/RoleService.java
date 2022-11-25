package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.Role;
import com.florinsutu.capstone.models.RoleType;
import com.florinsutu.capstone.repositories.RoleRepository;

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
            throw new NotFoundException("Role not available");

        return role.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public Role getByRole(RoleType roleType) {		
		Optional<Role> role = repository.findByRoleType(roleType);
		if (!role.isPresent())
			throw new NotFoundException("Role not available");
		return role.get();
	}

}
