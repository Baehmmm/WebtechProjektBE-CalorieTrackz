package htw.webtech.CalorieTrackz.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalorieNinjasResponse {
    private List<CalorieNinjasItem> items;

    public List<CalorieNinjasItem> getItems() {
        return items;
    }

    public void setItems(List<CalorieNinjasItem> items) {
        this.items = items;
    }
}