package com.aditya.stockexchange.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Company 
{
	@Id
	private String id;
	private String name;
	private String code;
	private String turnover;
	private String ceo;
	private String boardOfDirectors;
	private String stockExchangeNames;
	private String sectorName;
	private String description;
}