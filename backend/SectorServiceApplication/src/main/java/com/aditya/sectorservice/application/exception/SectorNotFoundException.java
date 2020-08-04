package com.aditya.sectorservice.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorNotFoundException extends Throwable
{
	private String message;
}
