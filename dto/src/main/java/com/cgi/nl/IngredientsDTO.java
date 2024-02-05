package com.cgi.nl;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class  IngredientsDTO {
    @JsonProperty
    @NotBlank(message = "Please provide the ingredients field itemName or check the property")
    private String itemName;
}
