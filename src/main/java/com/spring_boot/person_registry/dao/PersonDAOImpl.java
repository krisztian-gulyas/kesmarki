package com.spring_boot.person_registry.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.PersonDAO;
import com.spring_boot.person_registry.mappers.PersonRepository;
import com.spring_boot.person_registry.modell.Person;

public class PersonDAOImpl implements PersonDAO {
	@Autowired PersonRepository mapper;
	
	@Override
	public Map<Long, Person> findAll() {
		return mapper.findAll();
	}
	
	@Override
	public boolean isExist(String name) {
		return mapper.isExist(name);
	}
	
	@Override
	public long select(String name) {
		return mapper.select(name);
	}
	
	@Override
	public int insert(String name) {
		return mapper.insert(name);
	}
	
	@Override
	public int update(long personid, String name) {
		return mapper.update(personid, name);
	}
	
	@Override
	public int delete(long personid) {
		return mapper.delete(personid);
	}
}