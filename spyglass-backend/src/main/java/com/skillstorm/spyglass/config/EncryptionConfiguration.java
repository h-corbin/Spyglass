package com.skillstorm.spyglass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncryptionConfiguration {

	@Bean
	@Primary
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10); // 10 is the number of rounds
	}
}