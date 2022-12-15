package com.florinsutu.gestionedispositivi.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private HttpStatus status;
	private String message;
}
