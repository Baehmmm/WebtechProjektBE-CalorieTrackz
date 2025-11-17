package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.service.FoodEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Lebensmittel werden umgewandelt in JSON damit die Website mit den Daten etwas anfangen kann
 */
@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodEntryService service;



    // URL: GET http://localhost:8080/api/foods

    @GetMapping("/foods")
    public Iterable<FoodEntry> getAllFoodEntries() {

        return service.getAllFoodEntries();
    }



    // URL: GET http://localhost:8080/api/foods/1

    @GetMapping("/foods/{id}")
    public FoodEntry getFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        return service.getFoodEntry(foodId);
    }



    // URL: POST http://localhost:8080/api/foods


    @PostMapping("/foods")
    public FoodEntry createFoodEntry(@RequestBody FoodEntry foodEntry) {
        return service.saveFoodEntry(foodEntry);
    }



    // URL: DELETE http://localhost:8080/api/foods/1

    @DeleteMapping("/foods/{id}")
    public void deleteFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        service.deleteFoodEntry(foodId);
    }
}
