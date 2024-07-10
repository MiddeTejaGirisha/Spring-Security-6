package com.example.Security1;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
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
//public class SecurityConfig {
//
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("user")).roles("USER")
//				.build();
//
//		UserDetails manager = User.builder().username("manager").password(passwordEncoder.encode("manager"))
//				.roles("MANAGER").build();
//
//		return new InMemoryUserDetailsManager(user, manager);
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests((authReq) -> authReq.requestMatchers("/welcome", "/login", "/loginerror")
//						.permitAll().requestMatchers("/user").hasRole("USER").requestMatchers("/manager")
//						.hasRole("MANAGER").anyRequest().authenticated())
//				.formLogin(
//						(form) -> form.loginPage("/login").usernameParameter("username").passwordParameter("password")
//
//								.successHandler((request, response, authentication) -> {
//									if (authentication.getAuthorities().stream()
//											.anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER"))) {
//										response.sendRedirect("/manager");
//									} else if (authentication.getAuthorities().stream()
//											.anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
//										response.sendRedirect("/user");
//									}
//								}).permitAll())
//
//				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true)
//						.deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout=true").permitAll());
//
//		http.httpBasic(Customizer.withDefaults());
//
//		return http.build();
//	}
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll() // Allow access to the login page without authentication
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .anyRequest().authenticated() // Any other request needs to be authenticated
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/default")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                );
        return http.build();
    }
}