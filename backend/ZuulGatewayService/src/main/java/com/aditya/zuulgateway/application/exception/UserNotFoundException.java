package com.aditya.zuulgateway.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends Throwable
{
	private String message;
}
