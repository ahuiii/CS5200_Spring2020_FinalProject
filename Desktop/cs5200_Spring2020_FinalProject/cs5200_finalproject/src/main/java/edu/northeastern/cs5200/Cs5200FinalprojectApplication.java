package edu.northeastern.cs5200;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@EnableFeignClients
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Cs5200FinalprojectApplication {

  public static void main(String[] args) {
    SpringApplication.run(Cs5200FinalprojectApplication.class, args);
  }

  @Bean
  public Argon2PasswordEncoder argon2PasswordEncoder() {
    return new Argon2PasswordEncoder();
  }

  @Bean
  public Key generateSecretKey() {
    return Keys.secretKeyFor(SignatureAlgorithm.HS256);
  }

}
