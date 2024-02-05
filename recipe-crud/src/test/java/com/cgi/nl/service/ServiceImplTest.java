package com.cgi.nl.service;

import com.cgi.nl.*;
import com.cgi.nl.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {
    @InjectMocks
    private ServiceImpl service;
    @Mock
    private RecipieDTO dto;
    @Mock
    private RecepieRepo repo;
    @Mock
    private Recipe recipe;

    @Mock
    private RecipieSearchDTO searchDTO;

    @Test
    void addRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        when(repo.save(Mockito.<Recipe>any())).thenReturn(recipe);
        RecipieDTO recipie = new RecipieDTO();
        recipie.setIngredients(new HashSet<>());
        recipie.setInstructions("Instructions");
        recipie.setRecipieName("Recipie Name");
        recipie.setSuitableFor(1);
        recipie.setVeg(true);
        long resultId=service.addRecipe(dto);
        assertEquals(resultId, 1L);

    }

    @Test
    void updateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        Optional<Recipe> ofResult = Optional.of(recipe);

        Recipe recipe2 = new Recipe();
        recipe2.setId(1L);
        recipe2.setIngredients(new HashSet<>());
        recipe2.setInstructions("Instructions");
        recipe2.setRecipieName("Recipie Name");
        recipe2.setSuitableFor(1);
        recipe2.setVeg(true);
        when(repo.save(Mockito.<Recipe>any())).thenReturn(recipe2);
        when(repo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        RecipieDTO recipieDto = new RecipieDTO();
        recipieDto.setIngredients(new HashSet<>());
        recipieDto.setInstructions("Instructions");
        recipieDto.setRecipieName("Recipie Name");
        recipieDto.setSuitableFor(1);
        recipieDto.setVeg(true);
        long result = service.updateRecipe(1L, recipieDto);
        verify(repo).findById(Mockito.<Long>any());
        verify(repo).save(Mockito.<Recipe>any());
        assertEquals(1L, result);
    }


    @Test
    void deleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        Optional<Recipe> ofResult = Optional.of(recipe);
        doNothing().when(repo).deleteById(Mockito.<Long>any());
        when(repo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        long actualDeleteRecipeResult = service.deleteRecipe(1L);
        verify(repo).deleteById(Mockito.<Long>any());
        verify(repo).findById(Mockito.<Long>any());
        assertEquals(1L, actualDeleteRecipeResult);
    }
    @Test
    void exceptionDelete() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        Optional<Recipe> ofResult = Optional.of(recipe);
        doThrow(new ResourceNotFoundException("An error occurred")).when(repo).deleteById(Mockito.<Long>any());
        when(repo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteRecipe(1L));
    }


    @Test
    void getRecipes() {
        List<ResponseDTO> resultList=new ArrayList<>();
        List<ResponseDTO> result=service.getRecipes(searchDTO, 1,10 );
        assertEquals(result,resultList);
    }
}