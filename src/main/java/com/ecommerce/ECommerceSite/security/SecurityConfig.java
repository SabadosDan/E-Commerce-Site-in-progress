//package com.ecommerce.ECommerceSite.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests();
//                .antMatchers("/public/**").permitAll()
//                .antMatchers("/secured/**").access("hasAuthority('USER')")
//                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
//                .antMatchers("/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/dashboard")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl(("/logout"))
//                .permitAll();
//                .and()
//                .csrf().disable();   // for testing
//    }
//
//}
