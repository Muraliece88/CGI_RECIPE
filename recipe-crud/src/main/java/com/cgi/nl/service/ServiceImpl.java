package com.cgi.nl.service;


import com.cgi.nl.*;
import com.cgi.nl.exceptions.ResourceNotFoundException;
import com.cgi.nl.mappers.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ServiceImpl implements RecipeService{
    private RecepieRepo repo;
    private final RecipeMapper mappers = Mappers.getMapper(RecipeMapper.class);

    public ServiceImpl(RecepieRepo repo) {
        this.repo = repo;
    }

    /**
     * Method to add a recipe into the table
     * @param recipie
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public long addRecipe(RecipieDTO recipie) {

        Recipe createRecipie= repo.save(RecipeMapper.INSTANCE.mapRecipeResponse(recipie));
        log.info("Recipe with ID {} inserted"+createRecipie.getId());
        return createRecipie.getId();
    }

    /**
     * Method updates existing records else throws exception
     * @param id
     * @param recipieDto
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public long updateRecipe(long id, RecipieDTO recipieDto)
    {
        Recipe recipe= repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Recepie with id: " + id+"do not exists"));
        log.info("Recipe exists with ID and hence updating");
        recipieDto.setId(recipe.getId());
       Recipe updatedRecipe =mappers.INSTANCE.mapRecipe(recipieDto);
       repo.save(updatedRecipe);
        return updatedRecipe.getId();

    }

    /**
     * Method deletes entry from the table for the requested id
     * @param id
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public long deleteRecipe(long id) {

        Recipe recipe= repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Recepie with id: " + id+"do not exists"));
        log.info("Recipe exists with ID{} and hence deleting"+recipe.getId());
        repo.deleteById(id);
        return id;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ResponseDTO> getRecipes(RecipieSearchDTO searchDto, int pageNo, int size) {
        List<ResponseDTO> paginatedList;

            List<ResponseDTO>   response=  RecipeMapper.INSTANCE.entityToResponse( repo.findByConditions(searchDto.getSuitableFor(),searchDto.getVeg()
                    ,searchDto.getInstructions(),searchDto.getIngredientsInclude(),searchDto.getIngredientsExclude()));
            log.debug("fetched the results for matching condition: "+response.size());
            paginatedList= response.stream()
                    .skip((pageNo - 1) * size)
                    .limit(size)
                    .collect(Collectors.toList());
        return paginatedList;
    }

}
