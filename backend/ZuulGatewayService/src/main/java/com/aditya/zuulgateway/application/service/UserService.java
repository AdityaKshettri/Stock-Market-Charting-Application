package com.aditya.zuulgateway.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.aditya.zuulgateway.application.dto.UserDto;

public interface UserService extends UserDetailsService
{
	public UserDetails loadUserByUsername(String username);
	public UserDto signup(UserDto userDto);
	public UserDto activate(String token);
	public UserDto update(UserDto userDto);
}
