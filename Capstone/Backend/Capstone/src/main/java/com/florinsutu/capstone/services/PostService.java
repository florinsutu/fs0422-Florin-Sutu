package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post save(Post x) {
        return repository.save(x);
    }

    public List<Post> getAll() {
        return repository.findAll();
    }

    public List<Post> getAllFollowedPostsByUserId(Long id){
    	return repository.getPostsOfFollowed(id);
    }
    
    public List<Post> getAllUnfollowedPostsByUserId(Long id){
    	return repository.getPostsOfUnfollowed(id).stream().filter(p -> p.getAuthor().getId() != id).toList();
    }
    
    public Post getById(Long id) {

        Optional<Post> post = repository.findById(id);

        if(!post.isPresent())
            throw new NotFoundException("Post Doesn't exist");

        return post.get();
    }

    public String deleteById(Long id) {
        repository.deleteById(id);
        return("Post deleted successfully");
    }
    
    @Transactional
    public void deleteByAuthorId(Long id) {
		repository.deleteByAuthorId(id);
	}
    
    
  // ---------------------------- Paging --------------------------------  
    
	public Page<Post> getAllAndPaginate(Pageable p){
		Page<Post> pe = repository.findAll(p);
		return pe;
	}
	
//	-------------------------- Filtering ---------------------------------
	
	public Page<Post> getByAuthorAndPaginate(String author, Pageable p){
		return repository.findByAuthor(author, p);
	}
	
	public List<Post> getByAuthorId(Long id){
		return repository.findByAuthorId(id);
	}
	
	public List<User> getPostLikers(Long id){
		return repository.getPostLikers(id);
	}

	public void deletePostList(List<Post> userPosts) {
		for(Post p : userPosts) {
			repository.deleteById(p.getId());
		}
	}	
}
