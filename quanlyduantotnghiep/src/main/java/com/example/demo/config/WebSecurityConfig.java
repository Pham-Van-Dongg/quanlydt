package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Cấu hình PasswordEncoder (sử dụng BCrypt để mã hóa mật khẩu)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình AuthenticationManager để sử dụng CustomUserDetailsService và PasswordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(customUserDetailsService)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }

    // Cấu hình SecurityFilterChain để quản lý các yêu cầu HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/process_register" ,"/register").permitAll() // Cho phép truy cập không cần xác thực
                
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll() // Cho phép truy cập file tĩnh
                .anyRequest().authenticated() // Các yêu cầu khác cần xác thực
            )
            .formLogin(form -> form
                .loginPage("/login") // Trang đăng nhập tùy chỉnh
                .defaultSuccessUrl("/index", true) // Chuyển hướng sau khi đăng nhập thành công
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL để đăng xuất
                .logoutSuccessUrl("/login") // Chuyển hướng sau khi đăng xuất
                .permitAll()
            );
        return http.build();
    }
}
