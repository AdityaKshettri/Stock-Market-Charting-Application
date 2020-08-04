package com.aditya.companyservice.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.companyservice.application.dto.CompanyDto;
import com.aditya.companyservice.application.dto.IpoDto;
import com.aditya.companyservice.application.dto.StockPriceDto;
import com.aditya.companyservice.application.exception.CompanyNotFoundException;
import com.aditya.companyservice.application.service.CompanyService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/company-service/companies")
public class CompanyController 
{
	@Autowired
	private CompanyService companyService;
	
	@GetMapping(path = "")
	public ResponseEntity<List<CompanyDto>> getCompanies() 
	{
		return ResponseEntity
				.ok(companyService.getCompanies());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<CompanyDto> getCompanyDetails(@PathVariable String id)
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.findById(id);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(companyDto);
	}
	
	@GetMapping(path = "/match/{pattern}")
	public ResponseEntity<List<CompanyDto>> getMatchingCompanies(@PathVariable String pattern) 
	{
		return ResponseEntity.ok(companyService.getMatchingCompanies(pattern));
	}
	
	@GetMapping(path = "/{id}/ipos")
	public ResponseEntity<List<IpoDto>> getCompanyIpoDetails(@PathVariable String id)
			throws CompanyNotFoundException 
	{
		List<IpoDto> ipoDtos = companyService.getCompanyIpoDetails(id);
		if(ipoDtos == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(ipoDtos);
	}
	
	@GetMapping(path = "/{id}/stockPrices")
	public ResponseEntity<List<StockPriceDto>> getStockPrices(@PathVariable String id)
			throws CompanyNotFoundException
	{
		List<StockPriceDto> stockPriceDtos = companyService.getStockPrices(id);
		if(stockPriceDtos == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
	
	@PostMapping(path = "")
	@HystrixCommand(fallbackMethod = "defaultResponse")
	public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
		return ResponseEntity.ok(companyService.addCompany(companyDto));
	}
	
	@PutMapping(path = "")
	public ResponseEntity<CompanyDto> editCompany(@RequestBody CompanyDto companyDto)
			throws CompanyNotFoundException 
	{
		CompanyDto updatedCompanyDto = companyService.editCompany(companyDto);
		if(updatedCompanyDto == null) {
			throw new CompanyNotFoundException("Company not found at id : " + companyDto.getId());
		}
		return ResponseEntity.ok(updatedCompanyDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteCompany(@PathVariable String id) {
		companyService.deleteCompany(id);
	}
	
	/* Feign Client Mappings */
	
	@PostMapping(path = "/{companyName}/ipos")
	public void addIpoToCompany(@PathVariable String companyName, @RequestBody IpoDto ipoDto)
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addIpoToCompany(companyName, ipoDto);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not with name : " + companyName);
		}
	}
	
	@PostMapping(path = "/{companyCode}/stockPrices")
	public void addStockPriceToCompany(@PathVariable String companyCode, @RequestBody StockPriceDto stockPriceDto) 
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addStockPriceToCompany(companyCode, stockPriceDto);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not with code : " + companyCode);
		}
	}
	
	/* Feign Client Default Response */
	
	public ResponseEntity<?> defaultResponse(@RequestBody CompanyDto companyDto) {
		String err = "Fallback error as the microservice is down.";
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(err);
	}
}
