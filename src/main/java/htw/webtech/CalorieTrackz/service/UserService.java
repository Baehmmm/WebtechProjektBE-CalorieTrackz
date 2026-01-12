package htw.webtech.CalorieTrackz.service;

import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Import wichtig!

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CalorieCalculatorService calculatorService;
    private final PasswordEncoder passwordEncoder;

    // Konstruktor
    public UserService(UserRepository userRepository,
                       CalorieCalculatorService calculatorService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.calculatorService = calculatorService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User existiert bereits!");
        }

        //pw verschlüsseln
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public double getUserCalorieGoal(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));
        return calculatorService.calculateDailyCalories(user);
    }

    public UserEntity updateUserProfile(String username, UserEntity newUserData) {
        UserEntity existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));

        existingUser.setWeight(newUserData.getWeight());
        existingUser.setHeight(newUserData.getHeight());
        existingUser.setAge(newUserData.getAge());
        existingUser.setGender(newUserData.getGender());
        existingUser.setActivityLevel(newUserData.getActivityLevel());
        existingUser.setGoal(newUserData.getGoal());

        return userRepository.save(existingUser);
    }
}
