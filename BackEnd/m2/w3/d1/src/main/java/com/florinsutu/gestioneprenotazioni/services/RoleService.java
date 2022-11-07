package com.florinsutu.gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneprenotazioni.entities.Role;
import com.florinsutu.gestioneprenotazioni.repositories.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository rr;

    public void addRole(Role r) {
        rr.save(r);
    }
    public Optional<Role> getById(int id) {
        return rr.findById(id);
    }
}
