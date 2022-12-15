package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.Comment;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.repositories.CommentRepository;


@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public Comment getById(Long id) {

        Optional<Comment> comment = repository.findById(id);

        if(!comment.isPresent())
            throw new NotFoundException("Comment Doesn't exist");

        return comment.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
  // ---------------------------- Paging --------------------------------  
    
	public Page<Comment> getAllAndPaginate(Pageable p){
		Page<Comment> pe = repository.findAll(p);
		return pe;
	}
	
//	--------------------------- Filtering --------------------------------
    
	public Page<Comment> getByPostAndPaginate(Long id, Pageable p){
		return repository.getCommentsByPostId(id, p);
	}
	
	public Page<Comment> getBySenderAndPaginate(Long id, Pageable p){
		return repository.getCommentsBySenderId(id, p);
	}
	
	public List<User> getCommentLikers(Long id){
		return repository.getCommentLikers(id);
	}

	public List<Comment> getAllCommentsByPost(Long id) {
		return repository.findAllByPostId(id);
	}
	
}
