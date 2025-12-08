package htw.webtech.CalorieTrackz.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalorieNinjasItem {

    private String name;
    private double calories;

    @JsonAlias("protein_g")
    private double protein;

    @JsonAlias("carbohydrates_total_g")
    private double carbohydrates;

    @JsonAlias("fat_total_g")
    private double fat;

    // Getter und Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(double carbohydrates) { this.carbohydrates = carbohydrates; }

    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }
}