package com.florinsutu.gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.florinsutu.gestioneprenotazioni.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(
            "SELECT u FROM User u WHERE username LIKE concat('%', :u, '%' )"
    )
    Optional<User> findByUsername(@Param("u") String u);
    
    
}
