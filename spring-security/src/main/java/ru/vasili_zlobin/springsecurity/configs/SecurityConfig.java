package ru.vasili_zlobin.springsecurity.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.vasili_zlobin.springsecurity.services.AuthenticationService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/user_info").authenticated()
                .antMatchers("/admin").hasAnyAuthority("user.create", "user.edit")
                .anyRequest().permitAll()
                .and().formLogin()
                .and().logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(authenticationService);
        return authenticationProvider;
    }
}
