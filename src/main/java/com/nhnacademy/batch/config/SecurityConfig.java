//package com.nhnacademy.hello.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
////@Configuration
////@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
////public class SecurityConfig extends WebSecurityConfigurerAdapter {
////    @Bean
////    public BCryptPasswordEncoder encodePwd() {
////        return new BCryptPasswordEncoder();
////    }
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        http.authorizeHttpRequests()
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .anyRequest().permitAll()
////                .and()
////                .formLogin()
////                .loginPage("/loginForm")
////                .loginProcessingUrl("/login")
////                .defaultSuccessUrl("/");
////
////    }
////}
