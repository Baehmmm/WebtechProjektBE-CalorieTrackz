package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.api.OpenFoodFactsProduct;
import htw.webtech.CalorieTrackz.api.OpenFoodFactsSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class OpenFoodFactsService {

    private static final String SEARCH_URL = "https://world.openfoodfacts.org/cgi/search.pl?search_terms={query}&json=1";
    private final WebClient webClient = WebClient.create();

    /**
     * Sucht Produkte über den Namen in der Open Food Facts API.
     * @param query Der Suchbegriff.
     * @return Eine Liste von Produkten, die die API zurückliefert.
     */
    public Mono<List<OpenFoodFactsProduct>> searchProducts(String query) {
        return webClient.get()
                .uri(SEARCH_URL, query)
                .retrieve()
                // Mappe die JSON-Antwort auf unser DTO
                .bodyToMono(OpenFoodFactsSearchResponse.class)
                .map(response -> response.getProducts())
                .defaultIfEmpty(Collections.emptyList())
                .onErrorResume(e -> {
                    // Fehlerbehandlung: Bei Fehlern leere Liste zurückgeben
                    System.err.println("Fehler bei O.F.F. Suche: " + e.getMessage());
                    return Mono.just(Collections.emptyList());
                });
    }
}