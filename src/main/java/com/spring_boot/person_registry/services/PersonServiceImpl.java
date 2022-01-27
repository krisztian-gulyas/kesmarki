package com.spring_boot.person_registry.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.PersonDAO;
import com.spring_boot.person_registry.modell.Person;
import com.spring_boot.person_registry.services.impl.PersonService;

public class PersonServiceImpl implements PersonService {
	@Autowired PersonDAO personDAO;
	
	@Override
	public Map<Long, Person> findAll() {
		return personDAO.findAll();
	}
	
	@Override
	public boolean isExist(String name) {
		return personDAO.isExist(name);
	}
	
	@Override
	public long select(String name) {
		return personDAO.select(name);
	}
	
	@Override
	public int insert(String name) {
		return personDAO.insert(name);
	}
	
	@Override
	public int update(long personid, String name) {
		return personDAO.update(personid, name);
	}
	
	@Override
	public int delete(long personid) {
		return personDAO.delete(personid);
	}
}