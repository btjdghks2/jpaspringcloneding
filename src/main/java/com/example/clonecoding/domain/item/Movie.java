package com.example.clonecoding.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;
}
