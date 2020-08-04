package com.aditya.sectorservice.application.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aditya.sectorservice.application.dao.SectorRepository;
import com.aditya.sectorservice.application.model.Sector;

@Component
public class SectorServiceInitializer implements CommandLineRunner
{
	@Autowired
	private SectorRepository sectorRepository;
	
	@Override
	public void run(String... args) throws Exception 
	{
		sectorRepository.deleteAll();
		Sector sector1 = new Sector("Finance", "Companies that assist in Finance and Accounting");
		sectorRepository.save(sector1);
		Sector sector2 = new Sector("Healthcare Services", "Companies that provide Healthcare Services");
		sectorRepository.save(sector2);
		Sector sector3 = new Sector("Pharmaceuticals", "Companies that provide Medicines");
		sectorRepository.save(sector3);
		Sector sector4 = new Sector("Hostels", "Companies that provide with accomodation services");
		sectorRepository.save(sector4);
		Sector sector5 = new Sector("Internet Software and Services", "Companies that provide internet facilites as well as services");
		sectorRepository.save(sector5);
	}

}
