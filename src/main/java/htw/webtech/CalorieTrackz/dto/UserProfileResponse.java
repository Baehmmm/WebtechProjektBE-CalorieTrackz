package htw.webtech.CalorieTrackz.dto;

import htw.webtech.CalorieTrackz.enums.Goal;

public class UserProfileResponse {
    private String username;
    private Goal goal;
    private double currentWeight;
    private double targetCalories;

    // Konstruktor
    public UserProfileResponse(String username, Goal goal, double currentWeight, double targetCalories) {
        this.username = username;
        this.goal = goal;
        this.currentWeight = currentWeight;
        this.targetCalories = targetCalories;
    }

    // Getter
    public String getUsername() { return username; }
    public Goal getGoal() { return goal; }
    public double getCurrentWeight() { return currentWeight; }
    public double getTargetCalories() { return targetCalories; }
}