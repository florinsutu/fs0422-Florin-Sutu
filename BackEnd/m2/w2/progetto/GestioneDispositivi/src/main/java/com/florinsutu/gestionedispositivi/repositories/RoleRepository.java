package com.florinsutu.gestionedispositivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.gestionedispositivi.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
