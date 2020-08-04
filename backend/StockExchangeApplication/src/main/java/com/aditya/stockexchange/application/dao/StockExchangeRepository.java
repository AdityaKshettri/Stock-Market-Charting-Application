package com.aditya.stockexchange.application.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aditya.stockexchange.application.model.StockExchange;


@Repository
public interface StockExchangeRepository extends MongoRepository<StockExchange, String>
{
	public StockExchange findById(int id);
	public StockExchange findByName(String name);
}
