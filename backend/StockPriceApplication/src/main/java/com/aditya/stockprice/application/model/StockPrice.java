package com.aditya.stockprice.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class StockPrice 
{
	@Id
	private String id;
	private String companyCode;
	private String stockExchangeName;
	private double price;
	private String date;
	private String time;
}
