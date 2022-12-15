package com.florinsutu.gestionedispositivi.controllers;

import java.util.List;

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
import com.florinsutu.gestionedispositivi.entities.Laptop;
import com.florinsutu.gestionedispositivi.entities.Smartphone;
import com.florinsutu.gestionedispositivi.entities.Status;
import com.florinsutu.gestionedispositivi.entities.Tablet;
import com.florinsutu.gestionedispositivi.exceptions.DeviceNotFoundException;
import com.florinsutu.gestionedispositivi.services.DeviceService;
import com.florinsutu.gestionedispositivi.services.UserService;

@RestController
@RequestMapping("/api/devices") //TODO impostare la rotta
public class DeviceController {

	private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UserService userService;

// ----------------- Add Devices----------------------	
	
	@PostMapping("/smartphone")
	@PreAuthorize("hasRole('ADMIN')")
	public Device saveSmartphone() { 
		
		Device device = new Smartphone();
		device.setStatus(Status.AVAILABLE);
		
		logger.info("Device saved");
		return deviceService.save(device);
	}
	
	@PostMapping("/laptop")
	@PreAuthorize("hasRole('ADMIN')")
	public Device saveLaptop() { 
		
		Device device = new Laptop();
		device.setStatus(Status.AVAILABLE);
		
		logger.info("Device saved");
		return deviceService.save(device);
	}
	
	@PostMapping("/tablet")
	@PreAuthorize("hasRole('ADMIN')")
	public Device saveTablet() { 
		
		Device device = new Tablet();
		device.setStatus(Status.AVAILABLE);
		
		logger.info("Device saved");
		return deviceService.save(device);
	}

// --------------------- Get Devices ------------------------------	
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Device> getDeviceList() {
		return deviceService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Device getDeviceById(@PathVariable("id") Long id) {
	
			return deviceService.getById(id);
	
	}

// ---------------------- Update Devices ---------------------------	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Device assignDevice(
			@PathVariable("id") Long id,
			@RequestParam("userId") Long userId
			) {

		Device device = deviceService.getById(id);

		device.setUser(userService.getById(userId));
		device.setStatus(Status.ASSIGNED);
		
		deviceService.save(device);
		return device;
	}
	
	@PutMapping("/{id}/status")
	@PreAuthorize("hasRole('ADMIN')")
	public Device updateDevice(
			@PathVariable("id") Long id,
			@RequestParam("status") Status status
			) {

		Device device = deviceService.getById(id);

		device.setStatus(status);
		
		deviceService.save(device);
		return device;
	}
	
// --------------------- Delete Devices -------------------
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteDeviceById(@PathVariable("id") Long id) {
		deviceService.deleteById(id);
		return "Device deleted successfully";
	}

}
