package htw.webtech.CalorieTrackz.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretFoods {
    private List<FatSecretFood> food;

    public List<FatSecretFood> getFood() { return food; }
    public void setFood(List<FatSecretFood> food) { this.food = food; }
}