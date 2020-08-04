package com.aditya.zuulgateway.application.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil
{
	public static final long JWT_TOKEN_VALIDITY = 1000*60*60;
	
	@Value("${jwt.secret.key}")
	private String secret;
	
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret)
				.parseClaimsJws(token).getBody();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		String username = userDetails.getUsername();
		return doGenerateToken(claims, username);
	}
	
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
