package htw.webtech.CalorieTrackz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Lebensmittel werden umgewandelt in JSON damit die Website mit den Daten etwas anfangen kann
 */
@RestController
public class FoodController {

    @GetMapping("/api/foods")
    public List<FoodEntry> getAllFoodEntries() {
        // Beispiel Liste als Test
        return List.of(
                new FoodEntry(1L, "Apfel", 52),
                new FoodEntry(2L, "Banane", 89),
                new FoodEntry(3L, "Scheibe Vollkornbrot", 75)
        );
    }
}
