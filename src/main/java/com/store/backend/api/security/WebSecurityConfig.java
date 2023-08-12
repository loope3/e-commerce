package com.store.backend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final CustomPasswordEncoder passwordEncoder;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomUserDetailsService customUserDetailsService;
  

  public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService customUserDetailsService,
		  					CustomPasswordEncoder passwordEncoder) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.customUserDetailsService = customUserDetailsService;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 http.addFilterBefore(jwtAuthenticationFilter, AuthorizationFilter.class);
	 http.csrf(AbstractHttpConfigurer::disable)
    .cors(AbstractHttpConfigurer::disable)
    .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .formLogin(AbstractHttpConfigurer::disable)
    .securityMatcher("/**")
    .authorizeHttpRequests(registry -> registry
    		 .requestMatchers("/").permitAll()
    		 .requestMatchers("/product").permitAll()
    		 .requestMatchers("/auth/register").permitAll()
    		 .requestMatchers("/auth/login").permitAll()
    		 .requestMatchers("/admin").hasRole("ADMIN")
    		 .anyRequest().authenticated()
     );
    return http.build();
  }
  
  
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	  var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
	 builder.userDetailsService(customUserDetailsService)
	        .passwordEncoder(passwordEncoder.passwordEncoder());

	  return builder.build();
	  
  }

}