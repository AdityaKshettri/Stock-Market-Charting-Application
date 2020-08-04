package com.aditya.sectorservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDto 
{
	private String id;
	private String name;
	private String description;
}
