package com.cgi.nl.api;


import com.cgi.nl.service.ServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/recipies")
public class DeleteController {
    private final String path="/removeRecipie/";
    private static final Gson gson = new Gson();
    private final ServiceImpl serviceimpl;

    public DeleteController(ServiceImpl serviceimpl) {
        this.serviceimpl = serviceimpl;
    }

    @DeleteMapping(path+"{id}")
    private ResponseEntity<String> saveRecepie(@RequestHeader String traceId,@PathVariable long id)

    {
        log.info("Delete receipe ID "+id+ "triggered for "+traceId);
        return ResponseEntity.
                ok(gson.toJson("Recipe informated removed for the  ID "+
                        serviceimpl.deleteRecipe(id)));

    }
}
