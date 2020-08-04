package com.aditya.companyservice.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Ipo 
{
	@Id
	private String id;
	private String companyName;
	private String stockExchangeName;
	private double price;
	private int shares;
	private String openDateTime;
	private String remarks;
}
