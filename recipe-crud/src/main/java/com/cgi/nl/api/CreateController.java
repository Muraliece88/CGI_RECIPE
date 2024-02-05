package com.cgi.nl.api;

import com.cgi.nl.RecipieDTO;
import com.cgi.nl.service.ServiceImpl;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/recipies")

public class CreateController {
    private final String path="/createRecipe";
    private final ServiceImpl serviceimpl;
    private static final Gson gson = new Gson();

    public CreateController(ServiceImpl serviceimpl) {
        this.serviceimpl = serviceimpl;
    }

    @PostMapping(path = path,consumes = MediaType.APPLICATION_JSON_VALUE
                ,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> saveRecepie(@RequestHeader String traceId,
                                               @Valid @RequestBody RecipieDTO recipe)

    {
        log.info("Create receipe triggered for "+traceId);
        return ResponseEntity.
                ok(gson.toJson("Recipe created with ID "+
                        serviceimpl.addRecipe(recipe)+" successful"));
    }
}

