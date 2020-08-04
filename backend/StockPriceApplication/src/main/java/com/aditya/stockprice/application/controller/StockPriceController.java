package com.aditya.stockprice.application.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.stockprice.application.dto.CompanyCompareRequest;
import com.aditya.stockprice.application.dto.SectorCompareRequest;
import com.aditya.stockprice.application.dto.StockPriceDto;
import com.aditya.stockprice.application.exception.StockPriceNotFoundException;
import com.aditya.stockprice.application.service.StockPriceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/stock-price-service/stockPrices")
public class StockPriceController 
{
	@Autowired
	private StockPriceService stockPriceService;
	
	@GetMapping(path = "")
	public ResponseEntity<List<StockPriceDto>> findAll() {
		return ResponseEntity.ok(stockPriceService.findAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockPriceDto> findById(@PathVariable String id)
			throws StockPriceNotFoundException 
	{
		StockPriceDto stockPriceDto = stockPriceService.findById(id);
		if(stockPriceDto == null) {
			throw new StockPriceNotFoundException("Stock Price Not Found with id : " + id);
		}
		return ResponseEntity.ok(stockPriceDto);
	}
	
	@GetMapping(path = "/compareCompany")
	public ResponseEntity<?> companyComparison(@RequestBody CompanyCompareRequest compareRequest)
	{
		List<StockPriceDto> stockPriceDtos = null;
		try {
			stockPriceDtos = stockPriceService.getStockPricesForCompanyComparison(compareRequest);
		} catch (ParseException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Date Format");
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
	
	@GetMapping(path = "/compareSector")
	@HystrixCommand(fallbackMethod = "defaultResponse")
	public ResponseEntity<?> sectorComparison(@RequestBody SectorCompareRequest compareRequest)
	{
		List<StockPriceDto> stockPriceDtos = null;
		try {
			stockPriceDtos = stockPriceService.getStockPricesForSectorComparison(compareRequest);
		} catch (ParseException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Date Format");
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<?> save(@RequestBody List<StockPriceDto> stockPriceDtos) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(stockPriceService.save(stockPriceDtos));
	}
	
	@PutMapping(path = "")
	public ResponseEntity<StockPriceDto> update(@RequestBody StockPriceDto stockPriceDto)
			throws StockPriceNotFoundException
	{
		StockPriceDto updatedStockPriceDto = stockPriceService.update(stockPriceDto);
		if(updatedStockPriceDto == null) {
			throw new StockPriceNotFoundException("Stock Price not found with id : " + stockPriceDto.getId());
		}
		return ResponseEntity.ok(updatedStockPriceDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable String id) {
		stockPriceService.deleteById(id);
	}
	
	/* Feign Client Default Response */
	
	public ResponseEntity<?> defaultResponse(@RequestBody SectorCompareRequest compareRequest) {
		String err = "Fallback error as the microservice is down.";
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(err);
	}
}
