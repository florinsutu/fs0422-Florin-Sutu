package com.florinsutu.gestioneprenotazioni.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.florinsutu.gestioneprenotazioni.entities.User;
import com.florinsutu.gestioneprenotazioni.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository ur;

	public void saveUser(User u) {
		ur.save(u);
	}

	public void deleteUserById(int id) {
		ur.deleteById(id);
	}

	public Optional<User> getUserById(int id) {
		return ur.findById(id);
	}

	public Iterable<User> getAllUsers() {
		return ur.findAll();
	}

	public Page<User> getAllAndPaginate(Pageable p) {
		return ur.findAll(p);
	}

	public void refreshPasswords() {
		List<User> allUsers = ur.findAll();
		for (User u : allUsers) {
			u.setPassword(encoder.encode(u.getPassword()));
			saveUser(u);
		}

	}
}
