package com.cgi.nl;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecipieSearchDTO {

    @JsonProperty
    private String instructions;
    @JsonProperty
    private Integer suitableFor;
    @JsonProperty
    private Boolean veg;
    @JsonProperty
    private String ingredientsInclude;
    @JsonProperty
    private String ingredientsExclude;
}
