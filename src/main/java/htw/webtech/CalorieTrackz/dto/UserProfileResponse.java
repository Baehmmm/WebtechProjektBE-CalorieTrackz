package htw.webtech.CalorieTrackz.dto;

import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.enums.ActivityLevel;
import htw.webtech.CalorieTrackz.enums.Gender;
import htw.webtech.CalorieTrackz.enums.Goal;

public class UserProfileResponse {
    private String username;
    private Goal goal;
    private double currentWeight;
    private double targetCalories;
    private double height;
    private int age;
    private Gender gender;
    private ActivityLevel activityLevel;

    public UserProfileResponse(UserEntity user, double targetCalories) {
        this.username = user.getUsername();
        this.goal = user.getGoal();
        this.currentWeight = user.getWeight();
        this.targetCalories = targetCalories;
        this.height = user.getHeight();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.activityLevel = user.getActivityLevel();
    }

    public String getUsername() { return username; }
    public Goal getGoal() { return goal; }
    public double getCurrentWeight() { return currentWeight; }
    public double getTargetCalories() { return targetCalories; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public Gender getGender() { return gender; }
    public ActivityLevel getActivityLevel() { return activityLevel; }
}