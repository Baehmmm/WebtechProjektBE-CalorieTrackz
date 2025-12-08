package htw.webtech.CalorieTrackz.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretFood {

    @JsonAlias("food_name")
    private String foodName;

    @JsonAlias("food_description")
    private String foodDescription;
    @JsonAlias("food_id")
    private String foodId;

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getFoodDescription() { return foodDescription; }
    public void setFoodDescription(String foodDescription) { this.foodDescription = foodDescription; }

    public String getFoodId() { return foodId; }
    public void setFoodId(String foodId) { this.foodId = foodId; }
}