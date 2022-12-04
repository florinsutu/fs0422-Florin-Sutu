package com.florinsutu.capstone.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "messages")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;
    
    @ManyToOne
    private User sender;
    
    @ManyToOne
    private User receiver;
    
    //TODO perchè mi salva localdate e non localdatetime? magari aggiungiamo ricerca messaggi in un lasso di tempo
    private LocalDateTime date;
    
    private Boolean edited;
    
    
    
    
    
    
    
    
    
}
