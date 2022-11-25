package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<User> getAll(Pageable p) {
        return repository.findAll(p);
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
    
//	public Page<User> getAllAndPaginate(Pageable p){
//		Page<User> pe = ur.findAll(p);
//		return pe;
//	}
    
//    public Page<User> getByNameAndPaginate(String n, Pageable p){
//		return repository.findByNameAndPaginate(n, p);
//	}

    
}
