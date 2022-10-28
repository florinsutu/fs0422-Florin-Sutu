package com.florinsutu.progetto_m2w1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.progetto_m2w1.entities.Prenotazione;


@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

}
