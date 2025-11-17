package htw.webtech.CalorieTrackz.repository;

import htw.webtech.CalorieTrackz.FoodEntry;
import org.springframework.data.repository.CrudRepository;



public interface FoodEntryRepository extends CrudRepository<FoodEntry, Long> {
}
