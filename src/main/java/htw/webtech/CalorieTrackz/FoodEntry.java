package htw.webtech.CalorieTrackz;

import htw.webtech.CalorieTrackz.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class FoodEntry {

    /**
     * Lebensmittel Eingang
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private int calories;


    public FoodEntry() {
    }


    public FoodEntry(long id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }


    public FoodEntry(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }


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

    @Override
    public String toString() {
        return "FoodEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
