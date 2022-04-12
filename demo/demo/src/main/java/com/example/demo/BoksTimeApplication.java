package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import org.bson.internal.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@SpringBootApplication
public class BoksTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoksTimeApplication.class, args);
	}

	@PostMapping(value = "/api/jwt/decode",
	produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public String decodeJwt(@RequestBody String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];

		return new String(Base64.decode(payload), "UTF-8");

	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role("1", "ROLE_USER"));
			userService.saveRole(new Role("2", "ROLE_ADMIN"));
			userService.saveRole(new Role("3", "ROLE_BOXER"));
			userService.saveRole(new Role("4","ROLE_COACH"));
		};
	}

}
