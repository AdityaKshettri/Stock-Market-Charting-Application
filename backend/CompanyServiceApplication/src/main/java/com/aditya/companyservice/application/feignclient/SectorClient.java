package com.aditya.companyservice.application.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aditya.companyservice.application.dto.CompanyDto;

@FeignClient("sector-service")
public interface SectorClient 
{
	@PostMapping("/sectors/{sectorName}/companies")
	public void addCompanyToSector(@PathVariable String sectorName, @RequestBody CompanyDto companyDto);
}
