package com.florinsutu.capstone.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "SELECT p FROM Post p WHERE p.author.id = :id")
	List<Post> findByAuthorId(Long id);

	@Query(value = "SELECT p FROM Post p WHERE p.author.username LIKE %:author%")
	Page<Post> findByAuthor(String author, Pageable p);

	@Query(value = "SELECT p.likes FROM Post p WHERE p.id = :id")
	List<User> getPostLikers(Long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM POSTS where author_id IN "
			+ "(SELECT followed_id FROM users_followed WHERE user_id = :id ) ORDER BY date DESC")
	List<Post> getPostsOfFollowed(Long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM POSTS where author_id NOT IN "
			+ "(SELECT followed_id FROM users_followed WHERE user_id = :id) ORDER BY date DESC")
	List<Post> getPostsOfUnfollowed(Long id);

	@Modifying
	@Query(value = "DELETE FROM Post p WHERE p.author.id = :id")
	void deleteByAuthorId(Long id);
}

 //"SELECT l.book FROM Loan l WHERE l.user IN(SELECT u FROM User u WHERE u.cardNumber LIKE :cardNumber)"