package com.florinsutu.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
