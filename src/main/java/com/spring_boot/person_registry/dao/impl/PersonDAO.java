package com.spring_boot.person_registry.dao.impl;

import java.util.Map;

import com.spring_boot.person_registry.modell.Person;

public interface PersonDAO {
	Map<Long, Person> findAll();
	
	boolean isExist(String name);
	
	long select(String name);
	
	int insert(String name);
	
	int update(long personid, String name);
	
	int delete(long personid);
}