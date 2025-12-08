package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.api.CalorieNinjasResponse;
import htw.webtech.CalorieTrackz.api.CalorieNinjasItem;
import htw.webtech.CalorieTrackz.FoodEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalorieNinjasService {

    @Value("${calorieninjas.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public CalorieNinjasService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<FoodEntry> searchFood(String query) {
        String url = "https://api.calorieninjas.com/v1/nutrition?query=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CalorieNinjasResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, CalorieNinjasResponse.class
            );

            if (response.getBody() != null && response.getBody().getItems() != null) {
                return mapToFoodEntries(response.getBody().getItems());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei CalorieNinjas API: " + e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<FoodEntry> mapToFoodEntries(List<CalorieNinjasItem> ninjaItems) {
        List<FoodEntry> entries = new ArrayList<>();
        for (CalorieNinjasItem item : ninjaItems) {
            FoodEntry entry = new FoodEntry(
                    item.getName(),
                    (int) item.getCalories(),
                    item.getProtein(),
                    item.getCarbohydrates(),
                    item.getFat()
            );
            entries.add(entry);
        }
        return entries;
    }
}