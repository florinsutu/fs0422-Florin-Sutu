package com.florinsutu.capstone.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);

    @Query(
			nativeQuery = true,
			value = "SELECT * FROM users WHERE lower(name) LIKE %:n%"
	)
	Page<User> findByNameAndPaginate( String n, Pageable p);
    
    @Query( value = "SELECT u FROM User u INNER JOIN u.followed i WHERE i.id = :id" )
    List<User> getFollowers(Long id);
    
    @Query( value = "SELECT u.followed FROM User u WHERE u.id = :id" )
    List<User> getFollowed(Long id);
    
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM posts_likers c WHERE liker_id = :id ")
    void removePostLikesByUserId(Long id);
    
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM comments_likers c WHERE liker_id = :id ")
    void removeCommentLikesByUserId(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM users_followed c WHERE followed_id = :id ")
	void removeFollowersByUserId(Long id);
    
}
