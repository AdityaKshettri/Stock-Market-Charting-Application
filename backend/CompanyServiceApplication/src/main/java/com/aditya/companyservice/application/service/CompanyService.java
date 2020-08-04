package com.aditya.companyservice.application.service;

import java.util.List;

import com.aditya.companyservice.application.dto.CompanyDto;
import com.aditya.companyservice.application.dto.IpoDto;
import com.aditya.companyservice.application.dto.StockPriceDto;

public interface CompanyService 
{
	public List<CompanyDto> getCompanies();
	public CompanyDto findById(String id);
	public List<CompanyDto> getMatchingCompanies(String pattern);
	public CompanyDto addCompany(CompanyDto companyDto);
	public CompanyDto editCompany(CompanyDto companyDto);
	public void deleteCompany(String id);
	public CompanyDto addIpoToCompany(String companyName, IpoDto ipoDto);
	public List<IpoDto> getCompanyIpoDetails(String id);
	public CompanyDto addStockPriceToCompany(String companyCode, StockPriceDto stockPriceDto);
	public List<StockPriceDto> getStockPrices(String companyName);
}
