package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.repository.FoodEntryRepository;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FoodEntryTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private FoodEntryRepository foodRepo;
    @Autowired private UserRepository userRepo;

    @Test
    @WithMockUser(username = "hacker_user")
    @DisplayName("User darf nicht den Eintrag eines anderen Users löschen")
    void shouldNotDeleteOtherUsersEntry() throws Exception {
        UserEntity hacker = new UserEntity();
        hacker.setUsername("hacker_user");
        hacker.setPassword("pass123");
        userRepo.save(hacker);

        UserEntity victim = new UserEntity();
        victim.setUsername("victim_user");
        victim.setPassword("pass456");
        userRepo.save(victim);

        FoodEntry entry = new FoodEntry("Geheimdaten", 100, 10, 10, 10);
        entry.setUser(victim);
        foodRepo.save(entry);

        mockMvc.perform(delete("/api/foods/" + entry.getId()))
                .andExpect(status().isForbidden());
    }
}