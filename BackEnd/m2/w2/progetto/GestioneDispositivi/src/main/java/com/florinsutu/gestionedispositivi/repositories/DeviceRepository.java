package com.florinsutu.gestionedispositivi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florinsutu.gestionedispositivi.entities.Device;
import com.florinsutu.gestionedispositivi.entities.User;

public interface DeviceRepository extends JpaRepository<Device, Long>{

	public List<Device> findByUser(User user);
}
