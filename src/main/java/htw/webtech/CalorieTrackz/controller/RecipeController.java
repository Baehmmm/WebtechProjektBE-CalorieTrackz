package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.Recipe;
import htw.webtech.CalorieTrackz.service.RecipeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
// Falls du lokal auf Port 5173 und Render testest:
@CrossOrigin(origins = {"https://webtechprojektfe-calorietrackz.onrender.com", "http://localhost:5173"})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // GET: Lade meine Rezepte
    @GetMapping
    public List<Recipe> getMyRecipes(@AuthenticationPrincipal UserDetails userDetails) {
        return recipeService.getAllRecipesForUser(userDetails.getUsername());
    }

    // POST: Neues Rezept anlegen
    @PostMapping
    public Recipe createRecipe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Recipe recipe) {
        return recipeService.createRecipe(userDetails.getUsername(), recipe);
    }

    // DELETE: Rezept löschen
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
