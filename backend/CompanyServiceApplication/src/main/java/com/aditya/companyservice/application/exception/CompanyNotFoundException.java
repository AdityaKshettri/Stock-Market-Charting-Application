package com.aditya.companyservice.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyNotFoundException extends Throwable
{
	private String message;
}
