package htw.webtech.CalorieTrackz.repository;

import htw.webtech.CalorieTrackz.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Hilfsmethode, um User beim Login zu finden
    Optional<UserEntity> findByUsername(String username);
}
