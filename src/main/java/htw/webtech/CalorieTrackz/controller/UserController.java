package htw.webtech.CalorieTrackz.controller;

import htw.webtech.CalorieTrackz.UserEntity;
import htw.webtech.CalorieTrackz.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"https://webtechprojektfe-calorietrackz.onrender.com", "http://localhost:5173"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST: Neuen User registrieren
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.registerUser(user);
    }

    // GET: Berechneten Kalorienbedarf abrufen
    @GetMapping("/{id}/calories")
    public double getCalorieGoal(@PathVariable Long id) {
        return userService.getUserCalorieGoal(id);
    }
}
