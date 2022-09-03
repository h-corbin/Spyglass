package com.skillstorm.spyglass.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(encoder);
	}

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers("/register");
        
        http.authorizeHttpRequests().mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        
        http.authorizeHttpRequests().mvcMatchers("/goals/**").authenticated();
        http.authorizeHttpRequests().mvcMatchers("/users/**").authenticated();
        
        
        // logout is a post request to /logout
        http.authorizeHttpRequests().mvcMatchers("/logout/**").permitAll();
        http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
        
        return http.build();
    }
}
