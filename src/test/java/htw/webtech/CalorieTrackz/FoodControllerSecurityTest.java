package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.CalorieTrackzBeApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CalorieTrackzBeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FoodControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Sollte Zugriff ohne Token verweigern (403/401)")
    void shouldDenyUnauthenticatedAccess() throws Exception {
        this.mockMvc.perform(get("/api/foods"))
                .andExpect(status().isForbidden());
    }
}