package com.cgi.nl;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RecipieDTO {
    private Long Id;
    @JsonProperty
    @NotBlank(message = "Please provide fieldname: recipieName should be provided or check the property")
    private String recipieName;
    @JsonProperty
    @Size(min = 2, message = "instructions cannot be too short ")
    @Size(max = 200, message = "instructions cannot be too long")
    @NotBlank(message = "Please provide field name instructions or check the property")
    private String instructions;
    @JsonProperty
    @NotNull(message = "Please provide field name suitableFor or check the property")
    private Integer suitableFor;
    @JsonProperty
    @NotNull(message = "Please provide true or false for the field veg")
    private Boolean veg;
    @JsonProperty
    @NotNull(message = "Please provide the field: ingredients or check the property")
    private Set<IngredientsDTO> ingredients;
}
