package com.florinsutu.gestionedispositivi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.florinsutu.gestionedispositivi.security.JwtUtils;
import com.florinsutu.gestionedispositivi.security.LoginRequest;
import com.florinsutu.gestionedispositivi.security.LoginResponse;
import com.florinsutu.gestionedispositivi.security.UserDetailsImpl;
import com.florinsutu.gestionedispositivi.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

//	@Autowired TODO a cosa serve?
//	UserService userService;

	@Autowired
	JwtUtils jwtUtils;
	
	//TODO rimuovilo
//	eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCb3NzSmVmZiIsImlhdCI6MTY2NzU1ODM2MiwiZXhwIjoxNjY3NTgzMzYyfQ.PL2Xn_Wj7_dbdUyr2j-1IOIWZNiqsrxMoP24fUFb0x_I_UUDn737KkEGi0lWr7Zq9kAOXHz-QprEg6Dk37qF2w

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}

}
