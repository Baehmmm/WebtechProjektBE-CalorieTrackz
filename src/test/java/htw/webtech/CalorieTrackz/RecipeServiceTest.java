package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.Recipe;
import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.repository.RecipeRepository;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import htw.webtech.CalorieTrackz.service.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // NEUER IMPORT

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @MockitoBean
    private RecipeRepository recipeRepository;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Sollte ein Rezept für einen existierenden User speichern")
    void testCreateRecipe() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        doReturn(Optional.of(user)).when(userRepository).findByUsername("testuser");

        Recipe request = new Recipe();
        request.setName("Protein Shake");

        recipeService.createRecipe("testuser", request);

        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    @DisplayName("Sollte die Protein-Summe eines Rezepts korrekt berechnen")
    void testRecipeNutrientsCalculation() {
        Recipe recipe = new Recipe();
    }
}