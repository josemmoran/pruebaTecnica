package com.sv.serfinsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sv.serfinsa.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilte;
    private final AuthenticationProvider authProvider;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilt,AuthenticationProvider authProvider) {
		this.jwtAuthenticationFilte = jwtAuthenticationFilt;
		this.authProvider = authProvider;
	}
	
	@Bean
	SecurityFilterChain securirtyFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authRequest -> authRequest.requestMatchers("/auth/**")
						.permitAll()
						.anyRequest()
						.authenticated()
						).sessionManagement(sessionManager -> sessionManager
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.authenticationProvider(authProvider)
						.addFilterBefore(jwtAuthenticationFilte, UsernamePasswordAuthenticationFilter.class)
						.build();
	}
	
	
}
