package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.enums.ActivityLevel;
import htw.webtech.CalorieTrackz.enums.Gender;
import htw.webtech.CalorieTrackz.enums.Goal;
import htw.webtech.CalorieTrackz.service.CalorieCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CalorieCalculatorServiceTest {

    @Autowired
    private CalorieCalculatorService calculatorService;

    @Test
    @DisplayName("Sollte Kalorienbedarf für Muskelaufbau korrekt berechnen")
    void shouldCalculateCorrectMuscleBuildCalories() {
        UserEntity user = new UserEntity();
        user.setWeight(80.0);
        user.setHeight(180.0);
        user.setAge(30);
        user.setGender(Gender.MALE);
        user.setActivityLevel(ActivityLevel.LOW);
        user.setGoal(Goal.BUILD_MUSCLE);

        double result = calculatorService.calculateDailyCalories(user);

        assertEquals(2536.0, result, "Die Berechnung für Muskelaufbau ist inkorrekt");
    }

    @Test
    @DisplayName("Sollte BMR für Frauen korrekt berechnen")
    void shouldCalculateCorrectBmrForFemale() {
        UserEntity user = new UserEntity();
        user.setWeight(60.0);
        user.setHeight(165.0);
        user.setAge(25);
        user.setGender(Gender.FEMALE);
        user.setActivityLevel(ActivityLevel.LOW);
        user.setGoal(Goal.MAINTAIN_WEIGHT);

        double result = calculatorService.calculateBMR(user);
        assertEquals(1345.25, result, 0.01);
    }
}