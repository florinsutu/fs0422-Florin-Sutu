package com.florinsutu.gestionedispositivi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florinsutu.gestionedispositivi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameContaining(String name);

}
