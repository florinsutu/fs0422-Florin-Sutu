package com.florinsutu.capstone.models;

import java.time.LocalDateTime;
import java.util.Set;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
    @ManyToOne
    private User author;
    
    private String title;
    private String text;
    private LocalDateTime date;
    private Boolean edited;
    
    @OneToOne
    private Image image;

    
    @ManyToMany
    @JoinTable(
            name = "posts_likers",
            joinColumns = @JoinColumn(name = "post_id"),
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
