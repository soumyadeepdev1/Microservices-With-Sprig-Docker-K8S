//package com.university.administration.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // 1. Enable CORS using withDefaults()
//                // This will look for a bean named "corsConfigurationSource"
//                .cors(withDefaults())
//
//                // 2. Disable CSRF (Cross-Site Request Forgery)
//                // Often disabled for stateless APIs (like REST APIs)
//                .csrf(csrf -> csrf.disable())
//
//                // 3. Configure authorization rules
//                .authorizeHttpRequests(auth -> auth
//                        // Allow all requests for this example.
//                        // You can customize this to your needs, e.g., .requestMatchers("/api/**").permitAll()
//                        .requestMatchers("/actuator/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                //allows Auth header Basic <Token>
//                .httpBasic(withDefaults());
//        //Allows Auth header Bearer <Token>
////                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));;
//
//        return http.build();
//    }
//
//}
