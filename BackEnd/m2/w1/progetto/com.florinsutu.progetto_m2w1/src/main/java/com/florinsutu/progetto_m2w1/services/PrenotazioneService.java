package com.florinsutu.progetto_m2w1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.progetto_m2w1.entities.Prenotazione;
import com.florinsutu.progetto_m2w1.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	PrenotazioneRepository r;
	
	public void savePrenotazione(Prenotazione c) {
		r.save(c);
	}
	
	public List<Prenotazione> getAllPrenotaziones(){
		return r.findAll();
	}
	
	public void deletePrenotazioneById(int id) {
		r.deleteById(id);
	}
}
