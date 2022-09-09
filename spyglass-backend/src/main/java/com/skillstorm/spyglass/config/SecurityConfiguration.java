package com.skillstorm.spyglass.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



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
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().disable();
        
        http.authorizeHttpRequests().mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        
        http.authorizeHttpRequests().mvcMatchers("/goals/**").authenticated();
        http.authorizeHttpRequests().mvcMatchers("/users/**").authenticated();
        
        // logout is a post request to /logout
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
        
        return http.build();
    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
		// don't need to be authenticated for creating new users
        return (web) -> web.ignoring().antMatchers(HttpMethod.POST, "/users/");
    }
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(datasource);
		return jdbcUserDetailsManager;
	}
}
