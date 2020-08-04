package com.aditya.zuulgateway.application.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.zuulgateway.application.exception.UserAlreadyExistsException;
import com.aditya.zuulgateway.application.model.Mail;
import com.aditya.zuulgateway.application.exception.UserNotFoundException;
import com.aditya.zuulgateway.application.exception.InvalidTokenException;
import com.aditya.zuulgateway.application.config.JwtTokenUtil;
import com.aditya.zuulgateway.application.dto.SigninRequest;
import com.aditya.zuulgateway.application.dto.SigninResponse;
import com.aditya.zuulgateway.application.dto.UserDto;
import com.aditya.zuulgateway.application.service.MailService;
import com.aditya.zuulgateway.application.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody SigninRequest signinRequest)
	{
		final UserDetails userDetails = userService.loadUserByUsername(signinRequest.getUsername());
		if(userDetails == null || !passwordEncoder.matches(signinRequest.getPassword(), userDetails.getPassword())) {
			throw new UsernameNotFoundException("User not found... Invalid Credentials...!!");
		}
		final String token = jwtTokenUtil.generateToken(userDetails);
		final long expiresIn = (jwtTokenUtil.getExpirationDateFromToken(token).getTime()-(new Date()).getTime());
		final boolean isAdmin = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
		return ResponseEntity.ok(new SigninResponse(userDetails.getUsername(), token, isAdmin, expiresIn));
	}
	
	@PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto, HttpServletRequest request)
			throws UserAlreadyExistsException
	{
		userDto = userService.signup(userDto);
		if(userDto == null) {
			throw new UserAlreadyExistsException("User already exists with given username or email!!");
		}
		
		String appUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		Mail mail = new Mail();
		mail.setMailFrom("adikshettri1623@gmail.com");
		mail.setMailTo(userDto.getEmail());
		mail.setMailSubject("Email Confirmation");
		mail.setMailContent("To confirm you email-address, please click the link below:\n"
				+ appUrl + "/auth/confirm?token=" + userDto.getConfirmationToken());
		mailService.sendEmail(mail);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userDto);
	}
	
	@GetMapping(path = "/confirm")
	public ResponseEntity<?> activate(@RequestParam String token)
			throws InvalidTokenException
	{
		UserDto userDto = userService.activate(token);
		if(userDto == null) {
			throw new InvalidTokenException("Invalid token : " + token);
		}
		return ResponseEntity.ok("Email Successfully activated");
	}
	
	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody UserDto userDto)throws UserNotFoundException
	{
		UserDto updatedUserDto = userService.update(userDto);
		if(updatedUserDto == null) {
			throw new UserNotFoundException("User not found with id : " + userDto.getId());
		}
		return ResponseEntity.ok(updatedUserDto);
	}
}
