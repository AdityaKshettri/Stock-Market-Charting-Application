package com.aditya.zuulgateway.application.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aditya.zuulgateway.application.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthRequestFilter extends OncePerRequestFilter
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain
	) throws ServletException, IOException 
	{
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} 
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
			}
			catch (ExpiredJwtException e) {
				System.out.println("JWT token has expired");
			}
		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = userService.loadUserByUsername(username);
			if(jwtTokenUtil.validateToken(jwtToken, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
						UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new 
						WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	};
}
