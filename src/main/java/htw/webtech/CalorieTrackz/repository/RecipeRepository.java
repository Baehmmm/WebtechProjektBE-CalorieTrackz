package htw.webtech.CalorieTrackz.repository;

import htw.webtech.CalorieTrackz.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUser_Username(String username);

}
