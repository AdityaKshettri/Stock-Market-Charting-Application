package com.aditya.sectorservice.application.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Document
public class Sector 
{
	@Id
	private String id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String description;
	
	@DBRef
	private List<Company> companies = new ArrayList<>();
}
