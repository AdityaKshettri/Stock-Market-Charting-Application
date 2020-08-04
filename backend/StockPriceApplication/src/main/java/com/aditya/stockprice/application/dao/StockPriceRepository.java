package com.aditya.stockprice.application.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aditya.stockprice.application.model.StockPrice;

@Repository
public interface StockPriceRepository extends MongoRepository<StockPrice, String>
{
	public List<StockPrice> findByCompanyCodeAndStockExchangeName(String companyCode, String stockExchangeName); 
}
