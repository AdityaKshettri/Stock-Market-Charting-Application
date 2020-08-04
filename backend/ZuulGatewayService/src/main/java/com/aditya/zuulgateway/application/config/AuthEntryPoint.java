package com.aditya.zuulgateway.application.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint
{
	@Override
	public void commence(HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException) 
					throws IOException, ServletException 
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}
	
}
