package com.florinsutu.capstone.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Comment;
import com.florinsutu.capstone.models.User;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
    @Query( value = "SELECT c FROM Comment c WHERE c.post.id = :id" )
    Page<Comment> getCommentsByPostId(Long id, Pageable p);
    
    @Query( value = "SELECT c FROM Comment c WHERE c.sender.id = :id" )
    Page<Comment> getCommentsBySenderId(Long id, Pageable p);
    
    @Query( value = "SELECT c.likes FROM Comment c WHERE c.id = :id" )
	 List<User> getCommentLikers(Long id);
}