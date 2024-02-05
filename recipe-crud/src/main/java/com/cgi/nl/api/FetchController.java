package com.cgi.nl.api;

import com.cgi.nl.RecipieSearchDTO;
import com.cgi.nl.ResponseDTO;
import com.cgi.nl.service.ServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipies")

public class FetchController {
    private final String path="/getRecipies";
    private final ServiceImpl serviceimpl;



    public FetchController(ServiceImpl serviceimpl) {
        this.serviceimpl = serviceimpl;
    }

    @PostMapping(path = path,consumes = MediaType.APPLICATION_JSON_VALUE
                ,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> getRecepies(@RequestHeader String traceId,
                                                         @RequestParam int pageNo,
                                                         @RequestParam int size,
                                                         @RequestBody RecipieSearchDTO recipe)

    {
        List<ResponseDTO> responseList= serviceimpl.getRecipes(recipe,pageNo,size);
        if(responseList.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.ok(responseList);
        }

    }
}

