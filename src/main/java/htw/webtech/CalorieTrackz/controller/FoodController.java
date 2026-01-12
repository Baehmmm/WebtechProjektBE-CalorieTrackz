package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.service.FoodEntryService;
import htw.webtech.CalorieTrackz.service.CalorieNinjasService;
import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import htw.webtech.CalorieTrackz.service.FoodEntryService;
import htw.webtech.CalorieTrackz.service.CalorieNinjasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private CalorieNinjasService apiService;

    @Autowired
    private UserRepository userRepository;

    private UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));
    }

    @GetMapping("/foods")
    public Iterable<FoodEntry> getAllFoodEntries() {
        UserEntity currentUser = getCurrentUser();
        return service.getAllFoodEntries(currentUser);
    }

    @GetMapping("/foods/{id}")
    public FoodEntry getFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        return service.getFoodEntry(foodId);
    }

    @PostMapping("/foods")
    public FoodEntry createFoodEntry(@RequestBody FoodEntry foodEntry) {
        UserEntity currentUser = getCurrentUser();
        return service.saveFoodEntry(foodEntry, currentUser);
    }

    @DeleteMapping("/foods/{id}")
    public void deleteFoodEntry(@PathVariable String id) {
        Long foodId = Long.parseLong(id);
        service.deleteFoodEntry(foodId);
    }

    @GetMapping("/search")
    public List<FoodEntry> searchFood(@RequestParam String query) {
        return apiService.searchFood(query);
    }

}
