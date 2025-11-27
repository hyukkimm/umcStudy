package com.example.umc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.umc")
@EntityScan(basePackages = "com.example.umc.Entity")
public class UmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmcApplication.class, args);
    }

}
