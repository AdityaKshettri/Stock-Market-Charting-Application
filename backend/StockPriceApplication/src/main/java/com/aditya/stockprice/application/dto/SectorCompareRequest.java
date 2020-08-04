package com.aditya.stockprice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorCompareRequest 
{
	private String sectorName;
	private String stockExchangeName;
	private String fromPeriod;
	private String toPeriod;
	private String periodicity;
}
