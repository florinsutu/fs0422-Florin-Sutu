package com.florinsutu.gestionedispositivi.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Smartphone")
@NoArgsConstructor
public class Smartphone extends Device {

}
