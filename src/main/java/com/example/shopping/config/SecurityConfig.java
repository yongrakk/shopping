package com.example.shopping.config;

import com.example.shopping.handler.CustomAuthenticationFailureHandler;
import com.example.shopping.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    //Spring Security 유저 등록시 패스워드 처리
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Spring Security 처리 config
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/main","/productView").permitAll()
                .antMatchers("/userLikeList").authenticated().and()
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                                .loginProcessingUrl("/login_proc")
                                .defaultSuccessUrl("/main")
                                .successHandler(new CustomAuthenticationSuccessHandler())
                                .failureHandler(new CustomAuthenticationFailureHandler())
                                .permitAll()
                                .usernameParameter("username")
                                .passwordParameter("password")
                        /*.defaultSuccessUrl("/main")*/)
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }
}