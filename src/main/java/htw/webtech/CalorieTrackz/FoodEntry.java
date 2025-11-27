package htw.webtech.CalorieTrackz;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity

@Table(name = "foods")
public class FoodEntry {

    /**
     * Lebensmittel Eingang
     */

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
