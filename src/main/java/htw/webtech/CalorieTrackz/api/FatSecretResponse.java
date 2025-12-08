package htw.webtech.CalorieTrackz.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretResponse {
    private FatSecretFoods foods;

    public FatSecretFoods getFoods() { return foods; }
    public void setFoods(FatSecretFoods foods) { this.foods = foods; }
}


