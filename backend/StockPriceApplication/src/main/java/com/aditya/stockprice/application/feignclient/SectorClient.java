package com.aditya.stockprice.application.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aditya.stockprice.application.dto.CompanyDto;

@FeignClient("sector-service")
public interface SectorClient 
{
	@GetMapping("/sectors/{sectorName}/companies")
	public List<CompanyDto> getSectorCompanies(@PathVariable String sectorName);
}
