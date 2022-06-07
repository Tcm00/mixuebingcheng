package com.example.milktea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MilkTeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkTeaApplication.class, args);
    }

}
