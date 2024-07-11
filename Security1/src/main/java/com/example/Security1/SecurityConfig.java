package com.example.Security1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
////	private final CustomUserDetailsService customUserDetailsService;
////
////	@Autowired
////	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
////		this.customUserDetailsService = customUserDetailsService;
////	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//				.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/api/public/**").permitAll()
//						.anyRequest().authenticated()
//				)
//				.formLogin(Customizer.withDefaults())
//				.logout(Customizer.withDefaults());
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public UserDetailsService  UserDetailsService () {
//		// Define in-memory user details
//		UserDetails user = User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER")
//				.build();
//
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER", "ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
//
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(customUserDetailsService)
////				.passwordEncoder(passwordEncoder());
////	}
//
////	@Bean
////	public UserDetailsService userDetailsService() {
////		return customUserDetailsService;
////	}

//}


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf((csrf)->csrf.disable())
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers("/login").permitAll()
								.anyRequest().authenticated()
				)
				.formLogin(formLogin ->
						formLogin
								.loginPage("/login")
								//.defaultSuccessUrl("/default",true)
								//.permitAll()
								.successHandler((request, response, authentication) -> {
									if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
										response.sendRedirect("/admin");
									} else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
										response.sendRedirect("/user");
									} else {
										response.sendRedirect("/root");
									}
								})
				)
				.logout(logout ->
						logout
								.permitAll()
				);
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();

		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}