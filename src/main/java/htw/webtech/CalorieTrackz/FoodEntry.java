package htw.webtech.CalorieTrackz;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity

@Table(name = "foods")
public class FoodEntry {

    /**
     * Lebensmittel Eingang
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public FoodEntry() {
    }

    public FoodEntry(String name, int calories, double protein, double carbohydrates, double fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private int calories;

    @Column(name = "protein")
    private double protein;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Column(name = "fat")
    private double fat;


    @Override
    public String toString() {
        return "FoodEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
