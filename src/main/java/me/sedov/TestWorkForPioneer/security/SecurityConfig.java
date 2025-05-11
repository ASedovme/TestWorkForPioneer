package me.sedov.TestWorkForPioneer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/swagger-ui/index.html") // Укажите свой URL для страницы входа, если необходимо
//                        .permitAll() // Разрешить доступ к странице входа всем пользователям
//                )
//                .logout(logout -> logout.permitAll()); // Разрешить доступ к выходу всем пользователям
//
//        return http.build();
//    }
}
