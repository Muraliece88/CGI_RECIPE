package com.cgi.nl.service;


import com.cgi.nl.RecipieDTO;
import com.cgi.nl.RecipieSearchDTO;
import com.cgi.nl.ResponseDTO;
import com.cgi.nl.Recipe;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeService {
     @Transactional(isolation = Isolation.SERIALIZABLE)
     long addRecipe(RecipieDTO recipie);
     @Transactional(isolation = Isolation.SERIALIZABLE)
     long updateRecipe(long id, RecipieDTO recipie);
     @Transactional(isolation = Isolation.SERIALIZABLE)
     long deleteRecipe(long id);

     @Transactional(isolation = Isolation.SERIALIZABLE)
     List<ResponseDTO> getRecipes(RecipieSearchDTO recipieDto, int pageNo, int size);


}
