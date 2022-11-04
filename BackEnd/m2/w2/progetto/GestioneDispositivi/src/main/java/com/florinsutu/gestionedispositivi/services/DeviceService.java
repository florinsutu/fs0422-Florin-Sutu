package com.florinsutu.gestionedispositivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.gestionedispositivi.entities.Device;
import com.florinsutu.gestionedispositivi.entities.Status;
import com.florinsutu.gestionedispositivi.entities.User;
import com.florinsutu.gestionedispositivi.exceptions.DeviceNotFoundException;
import com.florinsutu.gestionedispositivi.repositories.DeviceRepository;

@Service
public class DeviceService {
	
	@Autowired
	private DeviceRepository repository;

	public Device save(Device x) {
		return repository.save(x);
	}

	public List<Device> getAll() {
		return repository.findAll();
	}

	public Device getById(Long id) {
		
		Optional<Device> device = repository.findById(id);
		
		if(!device.isPresent())
			throw new DeviceNotFoundException("Device not available");
		
		return device.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void giveBackAnyDevice(User user) {
		List<Device> list = repository.findByUser(user);
		for(Device d : list) {
			d.setUser(null);
			d.setStatus(Status.AVAILABLE);
			save(d);
		}
	}
	

}