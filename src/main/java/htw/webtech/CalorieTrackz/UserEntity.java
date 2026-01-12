package htw.webtech.CalorieTrackz;
import htw.webtech.CalorieTrackz.enums.ActivityLevel;
import htw.webtech.CalorieTrackz.enums.Gender;
import htw.webtech.CalorieTrackz.enums.Goal;
import jakarta.persistence.*;

    @Entity
    @Table(name = "app_users")
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Login-Daten
        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

        // Profil-Daten
        private int age;
        private double weight; // in kg
        private double height; // in cm

        @Enumerated(EnumType.STRING)
        private Gender gender;

        @Enumerated(EnumType.STRING)
        private ActivityLevel activityLevel;

        @Enumerated(EnumType.STRING)
        private Goal goal;


        public UserEntity() {}

        // Konstruktor
        public UserEntity(String username, String password, int age, double weight, double height, Gender gender, ActivityLevel activityLevel, Goal goal) {
            this.username = username;
            this.password = password;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.gender = gender;
            this.activityLevel = activityLevel;
            this.goal = goal;
        }

        // Getter + Setter
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }

        public double getHeight() { return height; }
        public void setHeight(double height) { this.height = height; }

        public Gender getGender() { return gender; }
        public void setGender(Gender gender) { this.gender = gender; }

        public ActivityLevel getActivityLevel() { return activityLevel; }
        public void setActivityLevel(ActivityLevel activityLevel) { this.activityLevel = activityLevel; }

        public Goal getGoal() { return goal; }
        public void setGoal(Goal goal) { this.goal = goal; }
    }

