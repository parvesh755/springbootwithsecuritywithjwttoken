package com.sca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
   @Autowired
   private JwtAuthenticationEntryPoint authenticationEntryPoint;
   
   @Autowired
   private UserDetailsService userDetailsService;
   
   @Autowired
   private JwtAuthenticationFilter filter;
   
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   }
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws
   Exception {
      return super.authenticationManagerBean();
   }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      .authorizeRequests().
      antMatchers("/login").permitAll()
      .antMatchers("/api/hello").permitAll()
      .antMatchers("/api/helloguys").permitAll() 
      .antMatchers("/api/adduser").permitAll()
      .antMatchers("/api/getusers").permitAll()
      .antMatchers("/api/updateusers/{id}").permitAll()
      .antMatchers("/api/userdelete/{id}").permitAll()
      .antMatchers("/api/getuserbyid/{id}").permitAll()
      .antMatchers("/api/users/firstname").permitAll()
      .antMatchers("/api/users/email").permitAll()
      
      .antMatchers("/api/addemployee").permitAll()
      .antMatchers("/api/getemployees").permitAll()
      .antMatchers("/api/getemployeebyempid/{empid}").permitAll()
      
      .anyRequest().authenticated().and()
      .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
   }
}