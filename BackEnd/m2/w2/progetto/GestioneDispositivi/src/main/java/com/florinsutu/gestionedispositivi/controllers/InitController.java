package com.florinsutu.gestionedispositivi.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestionedispositivi.entities.Role;
import com.florinsutu.gestionedispositivi.entities.RoleType;
import com.florinsutu.gestionedispositivi.entities.User;
import com.florinsutu.gestionedispositivi.services.RoleService;
import com.florinsutu.gestionedispositivi.services.UserService;

@RestController
@RequestMapping("/app") 
public class InitController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/add-roles")
	public String addRoles() {
		
		Role roleAdmin =  Role.builder()
				.roleType(RoleType.ROLE_ADMIN)
				.build();
		roleService.save(roleAdmin);

		Role roleUser = Role.builder()
				.roleType(RoleType.ROLE_USER)
				.build();
		roleService.save(roleUser);

		return "Roles added";
	}

	@GetMapping("/add-admin")
	public String addUser() {
		
		Set<Role> roles = new HashSet<Role>();
		Role admin = roleService.getById((long) 1);
		roles.add(admin);
		
		User user = User.builder()
				.firstname("Jeff")
				.lastname("Rowens")
				.email("rowen@gmail.com")
				.username("BossJeff")
				.active(true)
				.password("password")
				.roles(roles)
				.build();

		userService.save(user);

		return "Admin added";
	}


	
}
