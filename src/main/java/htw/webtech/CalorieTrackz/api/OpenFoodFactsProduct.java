package htw.webtech.CalorieTrackz.api;

public class OpenFoodFactsProduct {
    private String product_name;
    private Nutriments nutriments;

    public static class Nutriments {
        //gibt die Werte pro 100g mit dem Suffix '_100g' zurück
        private Double energy_kcal_100g;
        private Double proteins_100g;
        private Double carbohydrates_100g;
        private Double fat_100g;
    }
}

