package com.cgi.nl;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name="RECIPE", uniqueConstraints = @UniqueConstraint(columnNames = "recipieName"))

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "recipieName")
    private String recipieName;
    private int suitableFor;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="recipe_Id")
    private Set<Ingredients> ingredients;
    private String instructions;
    private Boolean  veg;

}

