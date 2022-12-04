package com.florinsutu.capstone.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Credentials
    
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    
    private String password;
    private Boolean active;
    
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public void addRole(Role role){
        this.roles.add(role);
    }
    
    // Info 
    
    private String name;
    private String lastname;
    private String description;
    private Boolean isOnline;
    private Boolean isPrivate;
    private LocalDateTime registration;
    
    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "users_followed",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> followed;
    
    
    public void follow(User user) {
    	this.followed.add(user);
    }
    
    public void unfollow(User user) {
    	this.followed.remove(user);
    }
    
}
