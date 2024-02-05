package com.cgi.nl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IngredientsDTOTest {

    @Test
    void testCanEqual() {

        assertFalse((new IngredientsDTO()).canEqual("Other"));
    }

    @Test
    void testEqual2() {

        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setItemName("Item Name");

        IngredientsDTO ingredientsDTO2 = new IngredientsDTO();
        ingredientsDTO2.setItemName("Item Name");
        assertTrue(ingredientsDTO.canEqual(ingredientsDTO2));
    }

    @Test
    void testEquals() {

        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setItemName("Item Name");

        assertNotEquals(ingredientsDTO, null);
    }


    @Test
    void testEquals2() {
        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setItemName("Item Name");
        assertNotEquals(ingredientsDTO, "Different type to IngredientsDTO");
    }



    @Test
    void testEqualsAndHashCode() {
        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setItemName("Item Name");
        assertEquals(ingredientsDTO, ingredientsDTO);
        int expectedHashCodeResult = ingredientsDTO.hashCode();
        assertEquals(expectedHashCodeResult, ingredientsDTO.hashCode());
    }


    @Test
    void testEqualsAndHashCode2() {
        IngredientsDTO ingredientsDTO = new IngredientsDTO();
        ingredientsDTO.setItemName("Item Name");
        IngredientsDTO ingredientsDTO2 = new IngredientsDTO();
        ingredientsDTO2.setItemName("Item Name");
        assertEquals(ingredientsDTO, ingredientsDTO2);
        int expectedHashCodeResult = ingredientsDTO.hashCode();
        assertEquals(expectedHashCodeResult, ingredientsDTO2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        IngredientsDTO actualIngredientsDTO = new IngredientsDTO();
        actualIngredientsDTO.setItemName("Item Name");
        String actualToStringResult = actualIngredientsDTO.toString();
        assertEquals("IngredientsDTO(itemName=Item Name)", actualToStringResult);
        assertEquals("Item Name", actualIngredientsDTO.getItemName());
    }
}
