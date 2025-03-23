package com.sv.serfinsa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sv.serfinsa.service.ServiceUsuarioDetail;

@Configuration
public class ApplicationConfig {

	@Autowired
	ServiceUsuarioDetail usuarioSer;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	 @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
	        return config.getAuthenticationManager();
	    }
	
	 @Bean
	 UserDetailsService userDetailService() {
		 return username -> usuarioSer.loadUserByUsername(username);
	 }
}
