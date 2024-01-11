package com.vinibortoletto.simpleshop.security;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final RequestMatcher[] WHITE_LIST = {
        new AntPathRequestMatcher("/swagger-ui/**"),
        new AntPathRequestMatcher("/swagger-ui.html"),
        new AntPathRequestMatcher("/v2/api-docs"),
        new AntPathRequestMatcher("/v3/api-docs/**"),
        new AntPathRequestMatcher("/users/login", HttpMethod.POST.name()),
        new AntPathRequestMatcher("/users", HttpMethod.POST.name()),
    };

    private static final RequestMatcher[] ADMIN_LIST = {
        new AntPathRequestMatcher("/users", HttpMethod.GET.name()),

        new AntPathRequestMatcher("/products", HttpMethod.PUT.name()),
        new AntPathRequestMatcher("/products", HttpMethod.POST.name()),
        new AntPathRequestMatcher("/products", HttpMethod.DELETE.name()),

        new AntPathRequestMatcher("/orders/status", HttpMethod.PUT.name()),

        new AntPathRequestMatcher("/categories/**", HttpMethod.GET.name()),
        new AntPathRequestMatcher("/categories", HttpMethod.POST.name()),
        new AntPathRequestMatcher("/categories/**", HttpMethod.PUT.name()),
        new AntPathRequestMatcher("/categories/**", HttpMethod.DELETE.name()),

        new AntPathRequestMatcher("/carts", HttpMethod.GET.name()),
        new AntPathRequestMatcher("/carts/**", HttpMethod.PUT.name()),

        new AntPathRequestMatcher("/carts/**", HttpMethod.PUT.name()),

        new AntPathRequestMatcher("/addresses", HttpMethod.GET.name()),

        new AntPathRequestMatcher("/customers", HttpMethod.GET.name()),
        new AntPathRequestMatcher("/admins", HttpMethod.GET.name()),

    };

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers(ADMIN_LIST).hasRole(Role.ADMIN.getRole())
                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(email -> userService.findByEmail(email));
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
