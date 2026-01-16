package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.repository.FoodEntryRepository;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import htw.webtech.CalorieTrackz.service.FoodEntryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Autowired
    private FoodEntryService service;

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

    @Test
    @DisplayName("Sollte alle Einträge eines Users für heute korrekt finden")
    void shouldFindEntriesForSpecificUserAndDate() {
        UserEntity user = new UserEntity();
        user.setUsername("query_user");
        user.setPassword("pass123");
        userRepo.save(user);

        LocalDate today = LocalDate.now();

        FoodEntry e1 = new FoodEntry("Frühstück", 300, 10, 40, 5);
        e1.setUser(user);
        e1.setDate(today);

        FoodEntry e2 = new FoodEntry("Mittag", 600, 30, 60, 15);
        e2.setUser(user);
        e2.setDate(today);

        foodRepo.save(e1);
        foodRepo.save(e2);

        List<FoodEntry> todaysList = service.getTodaysEntries(user);

        assertEquals(2, todaysList.size(), "Es sollten genau 2 Einträge für heute gefunden werden");
        double totalCals = todaysList.stream().mapToDouble(FoodEntry::getCalories).sum();
        assertEquals(900.0, totalCals, "Die Summe der Kalorien für heute sollte 900 sein");
    }
}