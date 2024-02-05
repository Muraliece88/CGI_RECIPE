package com.cgi.nl.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cgi.nl.Ingredients;
import com.cgi.nl.Recipe;
import com.cgi.nl.ResponseDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RecipeMapperImpl.class})
@ExtendWith(SpringExtension.class)
class RecipeMapperTest {
    @Autowired
    private RecipeMapper recipeMapper;
    @Test
    void testEntityToResponse() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setIngredients(new HashSet<>());
        recipe2.setInstructions("42");
        recipe2.setRecipieName("42");
        recipe2.setSuitableFor(Short.SIZE);
        recipe2.setVeg(false);

        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe2);
        recipes.add(recipe);
        assertEquals(2, recipeMapper.entityToResponse(recipes).size());
    }

    @Test
    void testEntityToResponse4() {
        Recipe recipe = mock(Recipe.class);
        when(recipe.getSuitableFor()).thenReturn(1);
        when(recipe.getVeg()).thenReturn(true);
        when(recipe.getId()).thenReturn(1L);
        when(recipe.getInstructions()).thenReturn("Instructions");
        when(recipe.getRecipieName()).thenReturn("Recipie Name");
        when(recipe.getIngredients()).thenReturn(new HashSet<>());
        doNothing().when(recipe).setId(Mockito.<Long>any());
        doNothing().when(recipe).setIngredients(Mockito.<Set<Ingredients>>any());
        doNothing().when(recipe).setInstructions(Mockito.<String>any());
        doNothing().when(recipe).setRecipieName(Mockito.<String>any());
        doNothing().when(recipe).setSuitableFor(anyInt());
        doNothing().when(recipe).setVeg(Mockito.<Boolean>any());
        recipe.setId(1L);
        recipe.setIngredients(new HashSet<>());
        recipe.setInstructions("Instructions");
        recipe.setRecipieName("Recipie Name");
        recipe.setSuitableFor(1);
        recipe.setVeg(true);
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        List<ResponseDTO> actualEntityToResponseResult = recipeMapper.entityToResponse(recipes);
        assertEquals(1, actualEntityToResponseResult.size());
    }
}
