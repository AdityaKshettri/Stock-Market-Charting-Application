package com.aditya.stockexchange.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.stockexchange.application.dto.CompanyDto;
import com.aditya.stockexchange.application.dto.StockExchangeDto;
import com.aditya.stockexchange.application.exception.StockExchangeNotFoundException;
import com.aditya.stockexchange.application.service.StockExchangeService;

@RestController
@RequestMapping("/stockExchanges")
public class StockExchangeController 
{
	@Autowired
	private StockExchangeService stockExchangeService;
	
	@GetMapping(path = "")
	public ResponseEntity<List<StockExchangeDto>> getStockExchangesList() {
		return ResponseEntity.ok(stockExchangeService.getStockExchangesList());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockExchangeDto> getStockExchangeDetails(@PathVariable String id)
			throws StockExchangeNotFoundException
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.findById(id);
		if(stockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(stockExchangeDto);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchangeDto stockExchangeDto) {
		return ResponseEntity.ok(stockExchangeService.addStockExchange(stockExchangeDto));
	}
	
	@PutMapping(path = "")
	public ResponseEntity<StockExchangeDto> editStockExchange(@RequestBody StockExchangeDto stockExchangeDto)
			throws StockExchangeNotFoundException 
	{
		StockExchangeDto updatedStockExchangeDto = stockExchangeService.editStockExchange(stockExchangeDto);
		if(updatedStockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + stockExchangeDto.getId());
		}
		return ResponseEntity.ok(updatedStockExchangeDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteStockExchange(@PathVariable String id) {
		stockExchangeService.deleteStockExchange(id);
	}
	
	@GetMapping(path = "/{id}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String id)
			throws StockExchangeNotFoundException  
	{
		List<CompanyDto> companyDtos = stockExchangeService.getCompanies(id);
		if(companyDtos == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(companyDtos);
	}
	
	/* Feign Client Mapping */
	
	@PostMapping(path = "/{stockExchangeName}/companies")
	public void addCompanyToStockExchange(@PathVariable String stockExchangeName, @RequestBody CompanyDto companyDto)
			throws StockExchangeNotFoundException  
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.addCompanyToStockExchange(stockExchangeName, companyDto);
		if(stockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found with name : " + stockExchangeName);
		}
	}
}
