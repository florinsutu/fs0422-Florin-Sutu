package com.florinsutu.gestioneprenotazioni.entities;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.florinsutu.gestioneprenotazioni.Security.StringConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buildings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	String name;
	String address;
	String city;
	
	@Length(min=8,max=8)
    @Convert(converter = StringConverter.class)
    private String securityCode;

	@JsonBackReference
	@OneToOne(mappedBy = "building", orphanRemoval = true)
	Workstation workstation;
	
}
