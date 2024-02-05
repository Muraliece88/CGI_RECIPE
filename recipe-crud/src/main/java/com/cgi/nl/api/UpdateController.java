package com.cgi.nl.api;

import com.cgi.nl.RecipieDTO;
import com.cgi.nl.service.ServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/recipies")
public class UpdateController {
    private final String path="/saveRecipie/";
    private final ServiceImpl serviceimpl;
    private static final Gson gson = new Gson();

    public UpdateController(ServiceImpl serviceimpl) {
        this.serviceimpl = serviceimpl;
    }

    @PutMapping(path+"{id}")
    private ResponseEntity<String> saveRecepie(@RequestHeader String traceId,@PathVariable long id,
                                               @RequestBody RecipieDTO recipe)

    {
        log.info("Update receipe ID "+id+ "triggered for "+traceId);
        return ResponseEntity.
                ok(gson.toJson("Recipe updated with ID "+
                        serviceimpl.updateRecipe(id,recipe)+" successful"));

    }
}

