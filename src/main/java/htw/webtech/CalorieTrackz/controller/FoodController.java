package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.service.FoodEntryService;
import htw.webtech.CalorieTrackz.service.OpenFoodFactsService;
import htw.webtech.CalorieTrackz.api.OpenFoodFactsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Lebensmittel werden umgewandelt in JSON damit die Website mit den Daten etwas anfangen kann
 */
@CrossOrigin(origins = "https://webtechprojektfe-calorietrackz.onrender.com")
@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodEntryService service;

    @Autowired // NEU
    private OpenFoodFactsService offService;


    @GetMapping("/foods")
    public Iterable<FoodEntry> getAllFoodEntries() {

        return service.getAllFoodEntries();
    }

    @GetMapping("/foods/{id}")
    public FoodEntry getFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        return service.getFoodEntry(foodId);
    }

    @PostMapping("/foods")
    public FoodEntry createFoodEntry(@RequestBody FoodEntry foodEntry) {
        return service.saveFoodEntry(foodEntry);
    }


    @DeleteMapping("/foods/{id}")
    public void deleteFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        service.deleteFoodEntry(foodId);
    }

    @GetMapping("/search")
    public Mono<List<OpenFoodFactsProduct>> searchFood(@RequestParam String query) {
        return offService.searchProducts(query);
    }
}
