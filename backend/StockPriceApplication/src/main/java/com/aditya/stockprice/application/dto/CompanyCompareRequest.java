package com.aditya.stockprice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCompareRequest 
{
	private String companyCode;
	private String stockExchangeName;
	private String fromPeriod;
	private String toPeriod;
	private String periodicity;
}
