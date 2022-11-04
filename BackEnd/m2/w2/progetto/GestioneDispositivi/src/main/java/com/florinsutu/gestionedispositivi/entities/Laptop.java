package com.florinsutu.gestionedispositivi.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Laptop")
@NoArgsConstructor
public class Laptop extends Device {

}
