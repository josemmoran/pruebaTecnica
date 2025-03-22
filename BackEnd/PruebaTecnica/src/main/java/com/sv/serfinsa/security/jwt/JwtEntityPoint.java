package com.sv.serfinsa.security.jwt;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtEntityPoint implements AuthenticationEntryPoint{

private static final Logger LOG = LoggerFactory.getLogger(JwtEntityPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOG.info("Error en commence");
		LOG.info("error "+authException, authException);
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario Invalido, ingrese un usuario correcto");
	}
}
