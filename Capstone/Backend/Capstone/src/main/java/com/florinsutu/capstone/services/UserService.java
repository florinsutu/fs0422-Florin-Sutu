package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User x) {
        return repository.save(x);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {

        Optional<User> user = repository.findById(id);

        if(!user.isPresent())
            throw new NotFoundException("User Doesn't exist");

        return user.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
  // ---------------------------- Paging --------------------------------  
    
	public Page<User> getAllAndPaginate(Pageable p){
		Page<User> pe = repository.findAll(p);
		return pe;
	}
    
    public Page<User> getByNameAndPaginate(String n, Pageable p){
		return repository.findByNameAndPaginate(n, p);
	}
    
  // -------------------------- Filter ----------------------------
    
    public List<User> getAllFollowers(Long id){
    	return repository.getFollowers(id);
    }
    
    public List<User> getAllFollowed(Long id){
    	return repository.getFollowed(id);
    }

    @Transactional
    public void removeRelationsByUserId(Long id) {
    	repository.removePostLikesByUserId(id);
    	repository.removeCommentLikesByUserId(id);
    	repository.removeFollowersByUserId(id);
    }
    
}
