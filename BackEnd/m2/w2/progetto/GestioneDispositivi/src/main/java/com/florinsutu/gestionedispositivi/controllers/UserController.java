package com.florinsutu.gestionedispositivi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestionedispositivi.entities.Device;
import com.florinsutu.gestionedispositivi.entities.Role;
import com.florinsutu.gestionedispositivi.entities.Status;
import com.florinsutu.gestionedispositivi.entities.User;
import com.florinsutu.gestionedispositivi.services.DeviceService;
import com.florinsutu.gestionedispositivi.services.RoleService;
import com.florinsutu.gestionedispositivi.services.UserService;

@RestController
@RequestMapping("/users") 
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;	
	@Autowired
	private DeviceService deviceService;
	
//	-------------------- Add User -------------------------
	
	@PostMapping
	public User saveUser(			
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="firstname",required=false) String firstname,
			@RequestParam(value="lastname",required=false) String lastname,
			@RequestParam(value="email",required=false) String email
	) {
		Set<Role> roles = new HashSet<Role>();
		Role role = roleService.getById((long) 2);
		roles.add(role);
		
		User user = User.builder()
				.firstname(firstname)
				.lastname(lastname)
				.email(email)
				.username(username)
				.active(true)
				.password(password)
				.roles(roles)
				.build();

		logger.info("New user created");
		return userService.save(user);
	}
	
//	----------------------- Get User ------------------------
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<User> getUserList() {
		return userService.getAll();
	}

	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}
	
//	-------------------- Update User --------------------
	

	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User updateUser(@PathVariable("id") Long id,
			@RequestParam(name="firstname", required = false) String firstname,
			@RequestParam(name="email", required = false) String email,
			@RequestParam(name="username", required = false) String username,
			@RequestParam(name="lastname", required = false) String lastname,
			@RequestParam(name="password", required = false) String password,
			@RequestParam(value="active",required=false) Boolean active
	) {

		User user = userService.getById(id);

		if(firstname != null) user.setFirstname(firstname);
		if(email != null) user.setEmail(email);
		if(username != null) user.setUsername(username);
		if(password != null) user.setPassword(password);
		if(lastname != null) user.setLastname(lastname);
		if(active != null) user.setActive(active);

		userService.save(user);
		return user;
	}
	
	@PutMapping("{id}/promote")
	@PreAuthorize("hasRole('ADMIN')")
	public User promoteUserToAdmin(@PathVariable("id") Long id
	) {
		
		Set<Role> roles = new HashSet<Role>();
		Role admin = roleService.getById((long) 1);
		roles.add(admin);

		User user = userService.getById(id);

		user.setRoles(roles);
		
		userService.save(user);
		return user;
	}
	

// -------------- Delete User -------------
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUserById(@PathVariable("id") Long id) {
		
		deviceService.giveBackAnyDevice(userService.getById(id));
		
		userService.deleteById(id);
		return "User deleted successfully";
	}
	
}
