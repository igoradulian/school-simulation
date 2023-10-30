package com.demo.firstspringapp.security;

import com.demo.firstspringapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        UserServiceImpl userService;


        public DaoAuthenticationProvider authenticationProvider()
        {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userService);
            authenticationProvider.setPasswordEncoder(passwordEncoder());

            return authenticationProvider;
        }

        @Bean
        public  BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder(12);
        }


        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(
                    auth -> auth.requestMatchers("/","/process-student","/form").permitAll()
                            .requestMatchers("/list-of-students", "/find-student-by-email"
                                    , "/find-student-by-email/{email}", "/search", "/process-search")
                            .hasAnyRole("STUDENT", "PROFESSOR")
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .successForwardUrl("/home")
                            .failureForwardUrl("/")
                            .permitAll())
                    .logout(
                            logout -> logout
                                    .invalidateHttpSession(true)
                                    .clearAuthentication(true)
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll()
                    );

           return http.build();
        }

}
