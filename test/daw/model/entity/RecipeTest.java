package daw.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeTest {

    @Test
    void testRecipeCreation() {
        Recipe recipe = new Recipe("Pasta", "Boil water and add pasta.");
        assertNotNull(recipe);
        assertEquals("Pasta", recipe.getName());
        assertEquals("Boil water and add pasta.", recipe.getInstructions());
    }

    @Test
    void testRecipeInstructions() {
        Recipe recipe = new Recipe("Salad", "Mix vegetables and dressing.");
        assertEquals("Mix vegetables and dressing.", recipe.getInstructions());
    }
}