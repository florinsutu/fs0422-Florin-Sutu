package com.florinsutu.gestioneprenotazioni.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.florinsutu.gestioneprenotazioni.entities.Building;

@Repository
public interface BuildingRepository extends PagingAndSortingRepository<Building, Integer>{

	@Query(
		nativeQuery = true,
		value = "SELECT * FROM buildings WHERE buildings.city LIKE :c"
	)
	public List<Building> findBuildingByCity(@Param("c") String c);
}
