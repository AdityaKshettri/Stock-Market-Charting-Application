package com.aditya.companyservice.application.mapper;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.aditya.companyservice.application.dto.IpoDto;
import com.aditya.companyservice.application.model.Ipo;

@Component
public class IpoMapper 
{
	public IpoDto toIpoDto(Ipo ipo) 
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		IpoDto ipoDto = mapper.map(ipo, IpoDto.class);
		return ipoDto;
	}
	
	public Ipo toIpo(IpoDto ipoDto) 
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Ipo ipo = mapper.map(ipoDto, Ipo.class);
		return ipo;
	}
	
	public List<IpoDto> toIpoDtos(List<Ipo> ipos)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<IpoDto> ipoDtos = Arrays.asList(mapper.map(ipos, IpoDto[].class));
		return ipoDtos;
	}
}
