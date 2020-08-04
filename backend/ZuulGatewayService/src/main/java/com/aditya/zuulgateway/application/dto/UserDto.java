package com.aditya.zuulgateway.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
	private String id;
	private String username;
	private String password;
	private String role;
	private String email;
	private String mobile;
	private String confirmationToken;
	private boolean confirmed;
}
