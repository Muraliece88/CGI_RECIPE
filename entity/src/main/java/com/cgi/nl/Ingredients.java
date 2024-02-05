package com.cgi.nl;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;
    private String itemName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;
}
