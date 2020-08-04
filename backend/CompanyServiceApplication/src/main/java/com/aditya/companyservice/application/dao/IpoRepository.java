package com.aditya.companyservice.application.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aditya.companyservice.application.model.Ipo;

@Repository
public interface IpoRepository extends MongoRepository<Ipo, String>
{
	public Optional<Ipo> findById(String id);
	public void deleteById(String id);
}
