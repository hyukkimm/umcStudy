//package com.example.umc;
//
//import com.example.umc.Entity.Enum.Gender;
//import com.example.umc.Entity.Enum.Status;
//import com.example.umc.Entity.User;
//import com.example.umc.Repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 이미 유저가 존재하면 추가하지 않음
//        if (userRepository.count() == 0) {
//            User user = new User();
//            user.setName("유저");
//            user.setNickname("user");
//            user.setEmail("user@naver.com");
//            user.setBirth("2000-01-01");
//            user.setGender(Gender.M);
//            user.setPoint(1000);
//            user.setStatus(Status.ACTIVE);
//            user.setCreatedAt(LocalDateTime.now());
//            user.setUpdatedAt(LocalDateTime.now());
//
//            userRepository.save(user);
//
//            System.out.println("하드코딩 유저 생성 완료: " + user.getName());
//        }
//    }
//}
