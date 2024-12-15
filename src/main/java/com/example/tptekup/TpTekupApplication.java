package com.example.tptekup;

import com.example.tptekup.Entities.User;
import com.example.tptekup.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpTekupApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpTekupApplication.class, args);
    }
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {

        };
    }
}
