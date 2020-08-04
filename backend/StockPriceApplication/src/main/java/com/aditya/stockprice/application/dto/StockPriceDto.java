package com.aditya.stockprice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceDto 
{
	private String id;
	private String companyCode;
	private String stockExchangeName;
	private double price;
	private String date;
	private String time;
}
