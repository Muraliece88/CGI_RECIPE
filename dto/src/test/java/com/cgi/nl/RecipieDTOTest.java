/*
package com.cgi.nl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RecipieDTOTest {

  @Test
  void testEquals() {

    RecipieDTO recipieDTO = new RecipieDTO();
    recipieDTO.setIngredients(new HashSet<>());
    recipieDTO.setInstructions("Instructions");
    recipieDTO.setRecipieName("Recipie Name");
    recipieDTO.setSuitableFor(1);
    recipieDTO.setVeg(true);
    assertNotEquals(recipieDTO, null);
  }

  @Test
  void testEquals2() {
    RecipieDTO recipieDTO = new RecipieDTO();
    recipieDTO.setIngredients(new HashSet<>());
    recipieDTO.setInstructions("Instructions");
    recipieDTO.setRecipieName("Recipie Name");
    recipieDTO.setSuitableFor(1);
    recipieDTO.setVeg(true);
    assertNotEquals(recipieDTO, "Different type to RecipieDTO");
  }

  @Test
  void testEquals3() {
    IngredientsDTO ingredientsDTO = new IngredientsDTO();
    ingredientsDTO.setItemName("Recipie Name");

    HashSet<IngredientsDTO> ingredients = new HashSet<>();
    ingredients.add(ingredientsDTO);

    RecipieDTO recipieDTO = new RecipieDTO();
    recipieDTO.setIngredients(ingredients);
    recipieDTO.setInstructions("Instructions");
    recipieDTO.setRecipieName("Recipie Name");
    recipieDTO.setSuitableFor(1);
    recipieDTO.setVeg(true);

    RecipieDTO recipieDTO2 = new RecipieDTO();
    recipieDTO2.setIngredients(new HashSet<>());
    recipieDTO2.setInstructions("Instructions");
    recipieDTO2.setRecipieName("Recipie Name");
    recipieDTO2.setSuitableFor(1);
    recipieDTO2.setVeg(true);

    assertNotEquals(recipieDTO, recipieDTO2);
  }

  @Test
  void testEqualsAndHashCode() {
    RecipieDTO recipieDTO = new RecipieDTO();
    recipieDTO.setIngredients(new HashSet<>());
    recipieDTO.setInstructions("Instructions");
    recipieDTO.setRecipieName("Recipie Name");
    recipieDTO.setSuitableFor(1);
    recipieDTO.setVeg(true);
    assertEquals(recipieDTO, recipieDTO);
    int expectedHashCodeResult = recipieDTO.hashCode();
    assertEquals(expectedHashCodeResult, recipieDTO.hashCode());
  }


  @Test
  void testEqualsAndHashCode2() {
    RecipieDTO recipieDTO = new RecipieDTO();
    recipieDTO.setIngredients(new HashSet<>());
    recipieDTO.setInstructions("Instructions");
    recipieDTO.setRecipieName("Recipie Name");
    recipieDTO.setSuitableFor(1);
    recipieDTO.setVeg(true);

    RecipieDTO recipieDTO2 = new RecipieDTO();
    recipieDTO2.setIngredients(new HashSet<>());
    recipieDTO2.setInstructions("Instructions");
    recipieDTO2.setRecipieName("Recipie Name");
    recipieDTO2.setSuitableFor(1);
    recipieDTO2.setVeg(true);

    // Act and Assert
    assertEquals(recipieDTO, recipieDTO2);
    int expectedHashCodeResult = recipieDTO.hashCode();
    assertEquals(expectedHashCodeResult, recipieDTO2.hashCode());
  }


  @Test
  void testGettersAndSetters() {

    RecipieDTO actualRecipieDTO = new RecipieDTO();
    HashSet<IngredientsDTO> ingredients = new HashSet<>();
    actualRecipieDTO.setIngredients(ingredients);
    actualRecipieDTO.setInstructions("Instructions");
    actualRecipieDTO.setRecipieName("Recipie Name");
    actualRecipieDTO.setSuitableFor(1);
    actualRecipieDTO.setVeg(true);
    String actualToStringResult = actualRecipieDTO.toString();
    Set<IngredientsDTO> actualIngredients = actualRecipieDTO.getIngredients();
    String actualInstructions = actualRecipieDTO.getInstructions();
    String actualRecipieName = actualRecipieDTO.getRecipieName();
    Integer actualSuitableFor = actualRecipieDTO.getSuitableFor();
    Boolean actualVeg = actualRecipieDTO.getVeg();

    assertEquals("Instructions", actualInstructions);
    assertEquals("Recipie Name", actualRecipieName);
    assertEquals(
        "RecipieDTO(recipieName=Recipie Name, instructions=Instructions, suitableFor=1, veg=true," + " ingredients=[])",
        actualToStringResult);
    assertEquals(1, actualSuitableFor.intValue());
    assertTrue(actualVeg);
    assertSame(ingredients, actualIngredients);
  }
}
*/
