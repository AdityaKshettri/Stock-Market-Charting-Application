package com.aditya.zuulgateway.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Document
public class User 
{
	@Id
	private String id;
	
	@NonNull
	private String username;
	
	@NonNull
	private String password;
	
	@NonNull
	private String email;
	
	@NonNull
	private String mobile;
	
	private String role;
	private String confirmationToken;
	private boolean confirmed;
}
