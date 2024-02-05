package com.cgi.nl;

import lombok.Data;

import java.util.Set;

@Data
public class ResponseDTO {
    private Long receipeId;
    private String recipieName;
    private Integer suitableFor;
    private String instructions;
    private Boolean veg;
    private Set<IngredientsDTO> ingredients;

}
