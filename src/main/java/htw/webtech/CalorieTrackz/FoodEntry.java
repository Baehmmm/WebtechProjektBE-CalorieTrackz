package htw.webtech.CalorieTrackz;
import jakarta.persistence.*;
import jakarta.persistence.Table;

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

    // NEU: Makronährstoffe (Angaben pro 100g sind üblich für APIs,
    // aber Sie speichern hier den Wert des Eintrags)
    @Column(name = "protein")
    private double protein;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Column(name = "fat")
    private double fat;

    // ... NEUE GETTER UND SETTER FÜR protein, carbohydrates, fat
    // ... Passen Sie auch die Konstruktoren und toString() an.

    // Beispiel für einen neuen Konstruktor:
    public FoodEntry(String name, int calories, double protein, double carbohydrates, double fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
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
