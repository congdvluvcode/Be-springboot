package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.Security;

@Configuration
public class DemoSecurityConfig {
    //add support JDBC, no more hardcode users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieving a user/member by userName
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id,pw,active from members where user_id=?"
        );

        //define query to retrieving the authorities/roles by userName
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id,role from roles where user_id=?"
        );

        return  jdbcUserDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("John")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(   HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(   HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(   HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(   HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(   HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );
        //USE http basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable cross site request forgery(CSRF)
        //in general, not required for stateless rest apis that use post,put,delete or/and patch
        http.csrf(csrf-> csrf.disable());
        return http.build();
    }
}
