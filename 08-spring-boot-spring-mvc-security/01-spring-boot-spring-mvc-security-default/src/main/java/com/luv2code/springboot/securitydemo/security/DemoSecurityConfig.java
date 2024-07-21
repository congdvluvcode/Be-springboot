package com.luv2code.springboot.securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for jdbc and no more hard code
    @Bean
    public UserDetailsManager userDetailsManager(DataSource theDataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(theDataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return userDetailsManager;
    }

//@Configuration
//public class DemoSecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails join = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//    return new InMemoryUserDetailsManager(join,mary,susan);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure->
                configure
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders").hasRole("MANAGER")
                        .requestMatchers("/systems").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout-> logout.permitAll())
                .exceptionHandling(configure->
                                configure.accessDeniedPage("/access-denied")
                );

    return http.build();
    }
}
