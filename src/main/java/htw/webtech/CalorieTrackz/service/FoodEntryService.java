package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.repository.FoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.access.AccessDeniedException;

@Service
public class FoodEntryService {

    @Autowired
    private FoodEntryRepository repo;




    public FoodEntry saveFoodEntry(FoodEntry foodEntry, UserEntity user) {
        foodEntry.setUser(user);
        return repo.save(foodEntry);
    }

    public FoodEntry getFoodEntry(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new RuntimeException("FoodEntry mit ID " + id + " nicht gefunden"));
    }


    public Iterable<FoodEntry> getAllFoodEntries(UserEntity user) {
        return repo.findAllByUser(user);
    }


    public void deleteFoodEntry(Long id, UserEntity currentUser) {
        FoodEntry entry = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Eintrag nicht gefunden"));

        if (!entry.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Das ist nicht dein Eintrag!");
        }

        repo.delete(entry);
    }

    public List<FoodEntry> getTodaysEntries(UserEntity user) {
        return repo.findAllByUserAndDate(user, LocalDate.now());
    }
}
