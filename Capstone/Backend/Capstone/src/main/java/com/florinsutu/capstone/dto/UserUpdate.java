package com.florinsutu.capstone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {

	private String name;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String description;
	private Boolean isPrivate; 
	
}
