package com.florinsutu.gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.gestioneprenotazioni.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
