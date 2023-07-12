//package com.ecommerce.ECommerceSite.config;
//
//import com.ecommerce.ECommerceSite.domain.dtos.User;
//import com.ecommerce.ECommerceSite.domain.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class DbInitializer {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DbInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder){
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void initializer() {
////        User user1 = new User();
////        user1.setUsername("user1");
////        user1.setEmail("u1@example.com");
////
////        String encodedPassword = passwordEncoder.encode("pass");
////        user1.setPassword(encodedPassword);
////
////        userRepository.save(user1);
//    }
//}
