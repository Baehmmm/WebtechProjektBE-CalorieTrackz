package htw.webtech.CalorieTrackz;

public class FoodEntry {


    /**
     Lebensmittel Eingang
     */

    private final long id;
    private final String name;
    private final int calories;

    public FoodEntry(long id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    // Getter sind wichtig für Spring Boot die
    // Objekte später automatisch in das JSON-Format umwandeln kann,
    // das der Browser versteht.
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}


