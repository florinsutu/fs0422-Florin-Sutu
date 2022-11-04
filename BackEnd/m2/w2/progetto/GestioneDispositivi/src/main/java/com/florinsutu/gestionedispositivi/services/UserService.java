package com.florinsutu.gestionedispositivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestionedispositivi.entities.User;
import com.florinsutu.gestionedispositivi.exceptions.UserNotFoundException;
import com.florinsutu.gestionedispositivi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User x) {
		return repository.save(x);
	}

	public List<User> getAll() {
		return repository.findAll();
	}

	public User getById(Long id) {

		Optional<User> user = repository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("User not available");

		return user.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public User findByName(String name) {
		Optional<User> user = repository.findByUsernameContaining(name);

		if (!user.isPresent())
			throw new UserNotFoundException("No user with that name found");

		return user.get();
	}

}
