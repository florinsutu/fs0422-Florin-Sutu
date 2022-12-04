package com.florinsutu.capstone.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	 @Query(value = "SELECT p FROM Post p WHERE p.author.username LIKE %:author%")
    Page<Post> findByAuthor(String author, Pageable p);
	 
	 //TODO metti anche la lista di utenti che ha messo mi piace
	 
	 @Query( value = "SELECT p.likes FROM Post p WHERE p.id = :id" )
	 List<User> getPostLikers(Long id);
}

