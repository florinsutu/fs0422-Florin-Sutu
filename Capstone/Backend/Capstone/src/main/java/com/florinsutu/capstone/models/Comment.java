package com.florinsutu.capstone.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
    private String text;
    private LocalDateTime date;
    private Boolean edited;
	   
    @ManyToOne
    private User sender;
	    
    @ManyToOne
    private Post post;
    
    @ManyToMany
    @JoinTable(
            name = "comments_likers",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "liker_id")
    )
    private Set<User> likes;
    
    public void addLike(User user) {
    	this.likes.add(user);
    }
    
    public void removeLike(User user) {
    	this.likes.remove(user);	
    }
	   
	
}
