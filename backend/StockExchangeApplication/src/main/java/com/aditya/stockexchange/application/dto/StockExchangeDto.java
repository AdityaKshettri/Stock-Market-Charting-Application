package com.aditya.stockexchange.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchangeDto 
{
	private String id;
	private String name;
	private String description;
	private String address;
	private String remarks;
}
