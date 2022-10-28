package com.florinsutu.progetto_m2w1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.progetto_m2w1.repositories.EdificioRepository;

@Service
public class EdificioService {

	@Autowired
	EdificioRepository r;
}
