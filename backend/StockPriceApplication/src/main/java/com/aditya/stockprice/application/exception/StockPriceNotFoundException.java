package com.aditya.stockprice.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceNotFoundException extends Throwable
{
	private String message;
}
