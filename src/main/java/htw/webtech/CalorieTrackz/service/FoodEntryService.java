package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.repository.FoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodEntryService {

    @Autowired
    private FoodEntryRepository repo;


    public FoodEntry saveFoodEntry(FoodEntry foodEntry) {
        return repo.save(foodEntry);
    }



    public FoodEntry getFoodEntry(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new RuntimeException("FoodEntry mit ID " + id + " nicht gefunden"));
    }


    public Iterable<FoodEntry> getAllFoodEntries() {
        return repo.findAll();
    }


    public void deleteFoodEntry(Long id) {
        repo.deleteById(id);
    }
}
