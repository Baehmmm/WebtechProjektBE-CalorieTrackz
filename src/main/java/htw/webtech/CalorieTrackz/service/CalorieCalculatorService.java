package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.enums.ActivityLevel;
import htw.webtech.CalorieTrackz.enums.Gender;
import htw.webtech.CalorieTrackz.enums.Goal;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculatorService {

    // 1. Grundumsatz (BMR) berechnen
    public double calculateBMR(UserEntity user) {
        // Formel: (10 * Gewicht) + (6.25 * Größe) - (5 * Alter) + s
        // s = +5 für Männer, -161 für Frauen
        double s = (user.getGender() == Gender.MALE) ? 5 : -161;

        return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + s;
    }

    // 2. Gesamtumsatz (TDEE) basierend auf Aktivität
    public double calculateDailyCalories(UserEntity user) {
        double bmr = calculateBMR(user);
        double multiplier = getActivityMultiplier(user.getActivityLevel());
        double maintenanceCalories = bmr * multiplier;

        if (user.getGoal() == null) return Math.round(maintenanceCalories);

        switch (user.getGoal()) {
            case BUILD_MUSCLE:
                return Math.round(maintenanceCalories + 400); // +400
            case LOSE_WEIGHT:
                return Math.round(maintenanceCalories - 400); // -400
            case MAINTAIN_WEIGHT:
                return Math.round(maintenanceCalories);       // +/- 0
            default:
                return Math.round(maintenanceCalories);
        }

    }

    private double getActivityMultiplier(ActivityLevel level) {
        switch (level) {
            case LOW: return 1.2;      // Wenig Bewegung
            case MODERATE: return 1.375; // Mittel
            case HIGH: return 1.55;    // Viel
            default: return 1.2;
        }
    }
}
