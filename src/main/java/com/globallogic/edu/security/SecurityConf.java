package com.globallogic.edu.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConf extends WebSecurityConfigurerAdapter{
    

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/order/route/delete/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/order/route/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/delete/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/newOrder").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/**").hasAnyAuthority("USER", "ADMIN")                
                .antMatchers(HttpMethod.POST, "/order").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/order/route/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error/403")
                .and().oauth2Login();
    }
}