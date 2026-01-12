package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.dto.UserProfileResponse;
import htw.webtech.CalorieTrackz.repository.UserRepository;
import htw.webtech.CalorieTrackz.service.CalorieCalculatorService;
import htw.webtech.CalorieTrackz.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"https://webtechprojektfe-calorietrackz.onrender.com", "http://localhost:5173"})
public class UserController {

    private final UserService userService;
    private final CalorieCalculatorService calorieCalculatorService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          CalorieCalculatorService calorieCalculatorService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.calorieCalculatorService = calorieCalculatorService;
        this.userRepository = userRepository;
    }

    private UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.registerUser(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile() {
        UserEntity user = getCurrentUser();

        double calculatedCalories = calorieCalculatorService.calculateDailyCalories(user);

        return ResponseEntity.ok(new UserProfileResponse(user, calculatedCalories));
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody UserEntity userUpdates) {
        UserEntity currentUser = getCurrentUser();

        UserEntity updatedUser = userService.updateUserProfile(currentUser.getUsername(), userUpdates);

        double newCalories = calorieCalculatorService.calculateDailyCalories(updatedUser);

        return ResponseEntity.ok(new UserProfileResponse(updatedUser, newCalories));
    }
}