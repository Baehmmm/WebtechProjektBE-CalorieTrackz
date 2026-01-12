package htw.webtech.CalorieTrackz.repository;

import htw.webtech.CalorieTrackz.FoodEntry;
import htw.webtech.CalorieTrackz.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FoodEntryRepository extends CrudRepository<FoodEntry, Long> {

    List<FoodEntry> findAllByUser(UserEntity user);

}