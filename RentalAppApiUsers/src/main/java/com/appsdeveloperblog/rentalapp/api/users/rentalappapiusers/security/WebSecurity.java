package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.security;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/**").hasIpAddress(this.environment.getProperty("geteway.ip"));
        http.headers().frameOptions().disable();
    }


}
