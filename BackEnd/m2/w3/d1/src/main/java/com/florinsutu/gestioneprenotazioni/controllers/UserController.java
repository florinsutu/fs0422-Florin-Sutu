package com.florinsutu.gestioneprenotazioni.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestioneprenotazioni.entities.User;
import com.florinsutu.gestioneprenotazioni.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService us;
	
	 @Autowired
	    PasswordEncoder encoder;
	 
	 // Es. 1b
	 
	 @GetMapping("/users/reset_password")
	 public void refreshPassword() {
		 us.refreshPasswords();
	 }
	
	
	//------------ GET ----------------
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
		return us.getUserById(id);
	}
	
	@GetMapping("/users")
	public ResponseEntity<Page<User>> getAllUsersAndPaginate(Pageable p){
		Page<User> res = us.getAllAndPaginate(p);
		if (res.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else{
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	
	//------------ POST -----------------
	
	@PostMapping("/users")
	public User postUser(
			@RequestParam("name") String full_name,
			@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password
	) {
		User u = User.builder()
				.fullName(full_name)
				.email(email)
				.username(username)
				.password(encoder.encode(password))
//				.password(password)
				.build();
		us.saveUser(u);
		
		return u;
	}
	//------------ PUT -----------------
	
	@PutMapping("/users/{id}")
	public User putUser(
			@PathVariable int id,
			@RequestParam(name="full_name", required = false) String full_name,
			@RequestParam(name="email", required = false) String email,
			@RequestParam(name="username", required = false) String username	
	) {
		Optional<User> u = getUserById(id);
		
		if (u.isPresent()) {
			User user = u.get();
			if(full_name != null) user.setFullName(full_name);
			if(email != null) user.setEmail(email);
			if(username != null) user.setUsername(username);
			
			us.saveUser(user);
			return user;			
		}
		
		return null;
	}
	//------------ DELETE -----------------
	
	@DeleteMapping("/users/{id}")
	public User deleteUserById(@PathVariable int id) {
		
		Optional<User> u = getUserById(id);
		
		if(u.isPresent()) {
			us.deleteUserById(id);
			return u.get();		
		}
		return null;
	}
}
