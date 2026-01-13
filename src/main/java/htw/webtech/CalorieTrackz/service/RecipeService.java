package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.Recipe;
import htw.webtech.CalorieTrackz.RecipeIngredient;
import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.repository.RecipeRepository;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    // 1. Alle Rezepte eines Users laden
    public List<Recipe> getAllRecipesForUser(String username) {
        return recipeRepository.findByUser_Username(username);
    }

    // 2. Ein neues Rezept erstellen
    @Transactional // Damit alles oder nichts gespeichert wird
    public Recipe createRecipe(String username, Recipe recipeRequest) {
        // User laden
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User nicht gefunden"));

        // Rezept-Objekt vorbereiten
        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.getName());
        recipe.setUser(user);

        // Zutaten aus dem Request nehmen und verknüpfen
        if (recipeRequest.getIngredients() != null) {
            List<RecipeIngredient> ingredients = recipeRequest.getIngredients().stream()
                    .map(ing -> {
                        // Neue Zutat erstellen
                        RecipeIngredient newIng = new RecipeIngredient(
                                ing.getName(),
                                ing.getCalories(),
                                ing.getProtein(),
                                ing.getCarbohydrates(),
                                ing.getFat()
                        );
                        newIng.setRecipe(recipe);
                        return newIng;
                    }).collect(Collectors.toList());

            recipe.setIngredients(ingredients);
        }

        return recipeRepository.save(recipe);
    }

    // 3. Ein Rezept löschen
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
