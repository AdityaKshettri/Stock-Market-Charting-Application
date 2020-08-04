package com.aditya.stockexchange.application.mapper;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.aditya.stockexchange.application.dto.StockExchangeDto;
import com.aditya.stockexchange.application.model.StockExchange;


@Component
public class StockExchangeMapper 
{
	public StockExchangeDto toStockExchangeDto(StockExchange stockExchange)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchangeDto stockExchangeDto = mapper.map(stockExchange, StockExchangeDto.class);
		return stockExchangeDto;
	}
	
	public StockExchange toStockExchange(StockExchangeDto stockExchangeDto)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchange stockExchange = mapper.map(stockExchangeDto, StockExchange.class);
		return stockExchange;
	}
	
	public List<StockExchangeDto> toStockExchangeDtos(List<StockExchange> stockExchanges)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<StockExchangeDto> stockExchangeDtos = Arrays.asList(mapper.map(stockExchanges, StockExchangeDto[].class));
		return stockExchangeDtos;
	}
}
