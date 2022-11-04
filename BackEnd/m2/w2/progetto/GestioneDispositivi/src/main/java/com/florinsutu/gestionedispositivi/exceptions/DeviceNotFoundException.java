package com.florinsutu.gestionedispositivi.exceptions;

public class DeviceNotFoundException extends RuntimeException {
	public DeviceNotFoundException(String message) {
		super(message);
	}
}
