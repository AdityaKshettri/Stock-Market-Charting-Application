package com.aditya.companyservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IpoDto 
{
	private String id;
	private String companyName;
	private String stockExchangeName;
	private double price;
	private int shares;
	private String openDateTime;
	private String remarks;
}
