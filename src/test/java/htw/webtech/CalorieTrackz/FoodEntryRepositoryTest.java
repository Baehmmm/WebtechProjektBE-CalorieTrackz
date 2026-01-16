package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.repository.FoodEntryRepository;
import htw.webtech.CalorieTrackz.repository.UserRepository; // NEU
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional; // EMPFOHLEN

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CalorieTrackzBeApplication.class)
@ActiveProfiles("test")
@Transactional
public class FoodEntryRepositoryTest {

    @Autowired
    private FoodEntryRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    void shouldSaveAndFindFoodEntry() {
        FoodEntry entry = new FoodEntry("Apfel", 52.0, 0.3, 14.0, 0.2);
        FoodEntry saved = foodRepo.save(entry);

        assertNotNull(saved.getId());
        assertEquals("Apfel", foodRepo.findById(saved.getId()).get().getName());
    }

    @Test
    @DisplayName("Sollte nur Einträge eines bestimmten Users finden")
    void shouldFindOnlyUserSpecificEntries() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("user1");
        user1.setPassword("pass123");
        userRepo.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setUsername("user2");
        user2.setPassword("pass456");
        userRepo.save(user2);

        FoodEntry e1 = new FoodEntry("Apfel", 50, 0, 10, 0);
        e1.setUser(user1);
        foodRepo.save(e1);

        FoodEntry e2 = new FoodEntry("Banane", 100, 1, 20, 0);
        e2.setUser(user2);
        foodRepo.save(e2);

        List<FoodEntry> result = (List<FoodEntry>) foodRepo.findAllByUser(user1);

        assertEquals(1, result.size());
        assertEquals("Apfel", result.get(0).getName());
    }
}