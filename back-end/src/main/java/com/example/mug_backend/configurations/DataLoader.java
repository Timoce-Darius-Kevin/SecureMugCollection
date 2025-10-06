// package com.example.mug_backend.configurations;

// import com.example.mug_backend.constants.Roles;
// import com.example.mug_backend.model.User;
// import com.example.mug_backend.model.Mug;
// import com.example.mug_backend.repository.UserRepository;
// import com.example.mug_backend.repository.MugRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// @Component
// public class DataLoader implements CommandLineRunner {

//     private final UserRepository userRepository;
//     private final MugRepository mugRepository;

//     public DataLoader(UserRepository userRepository, MugRepository mugRepository) {
//         this.userRepository = userRepository;
//         this.mugRepository = mugRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         // Create sample users
//         User adminUser = new User("auth0|123456789", "admin@example.com", "Admin User", Roles.ADMIN);
//         User regularUser = new User("auth0|987654321", "user@example.com", "Regular User", Roles.USER);
        
//         userRepository.save(adminUser);
//         userRepository.save(regularUser);

//         // Create sample mugs
//         Mug mug1 = new Mug("Coffee Master", "Ceramic", "Perfect for morning coffee", 8.5, adminUser);
//         Mug mug2 = new Mug("Tea Lover", "Porcelain", "Elegant tea cup", 7.0, regularUser);
//         Mug mug3 = new Mug("Office Mug", "Glass", "Modern glass mug", 9.0, adminUser);
        
//         mugRepository.save(mug1);
//         mugRepository.save(mug2);
//         mugRepository.save(mug3);
        
//         System.out.println("Sample data loaded successfully!");
//     }
// }