package com.florinsutu.capstone.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Role;
import com.florinsutu.capstone.models.RoleType;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType(RoleType roleType);
	
}
