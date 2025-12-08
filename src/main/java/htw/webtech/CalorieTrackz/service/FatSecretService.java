package htw.webtech.CalorieTrackz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import htw.webtech.CalorieTrackz.api.FatSecretResponse;
import htw.webtech.CalorieTrackz.api.FatSecretFood;
import htw.webtech.CalorieTrackz.FoodEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FatSecretService {

    @Value("${fatsecret.client-id}")
    private String clientId;

    @Value("${fatsecret.client-secret}")
    private String clientSecret;

    private String accessToken;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Hilft beim manuellen Debuggen

    public FatSecretService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    private void ensureToken() {
        if (this.accessToken != null) return;

        System.out.println("--- Hole neuen Access Token ---");
        String url = "https://oauth.fatsecret.com/connect/token";
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials&scope=basic", headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("Token Antwort: " + response.getBody());

            var map = objectMapper.readValue(response.getBody(), java.util.Map.class);
            if (map.containsKey("access_token")) {
                this.accessToken = (String) map.get("access_token");
                System.out.println("Token erfolgreich gesetzt!");
            }
        } catch (Exception e) {
            System.out.println("!!! FEHLER beim Token holen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<FoodEntry> searchFood(String query) {
        System.out.println("--- Starte Suche nach: " + query + " ---");
        ensureToken();

        if (this.accessToken == null) {
            System.out.println("!!! ABBRUCH: Kein Access Token vorhanden.");
            return new ArrayList<>();
        }

        String url = "https://platform.fatsecret.com/rest/server.api?method=foods.search&format=json&search_expression=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // holen rohen String, um zu sehen, was FatSecret schickt
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("FatSecret Raw JSON Antwort: " + response.getBody());

            // wandeln in Objekte um
            FatSecretResponse fsResponse = objectMapper.readValue(response.getBody(), FatSecretResponse.class);

            if (fsResponse != null && fsResponse.getFoods() != null) {
                System.out.println("Gefundene Einträge: " + (fsResponse.getFoods().getFood() != null ? fsResponse.getFoods().getFood().size() : "0"));
                return mapToFoodEntries(fsResponse.getFoods().getFood());
            } else {
                System.out.println("Antwort war okay, aber 'foods' war leer oder null.");
            }
        } catch (Exception e) {
            System.out.println("!!! FEHLER bei der Suche: " + e.getMessage());
            e.printStackTrace();
            this.accessToken = null; // Token resetten falls ungültig
        }

        return new ArrayList<>();
    }

    private List<FoodEntry> mapToFoodEntries(List<FatSecretFood> fatSecretFoods) {
        List<FoodEntry> entries = new ArrayList<>();
        if (fatSecretFoods == null) return entries;

        for (FatSecretFood fsFood : fatSecretFoods) {
            String desc = fsFood.getFoodDescription();
            int calories = (int) parseValue(desc, "Calories");
            double protein = parseValue(desc, "Protein");
            double carbs = parseValue(desc, "Carbs");
            double fat = parseValue(desc, "Fat");

            FoodEntry entry = new FoodEntry(fsFood.getFoodName(), calories, protein, carbs, fat);
            entries.add(entry);
        }
        return entries;
    }

    private double parseValue(String text, String key) {
        Pattern pattern = Pattern.compile(key + ":\\s*(\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0.0;
    }
}