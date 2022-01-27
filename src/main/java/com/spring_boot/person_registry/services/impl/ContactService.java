package com.spring_boot.person_registry.services.impl;

import java.util.Map;

import com.spring_boot.person_registry.modell.Contact;

public interface ContactService {
	Map<Long, Contact> findAll();
	
	boolean isExist(String email, String phone);
	
	long select(String email, String phone);
	
	Contact[] selectByAddressID(long addressid);
	
	int insert(long addressid, String email, String phone);
	
	int update(long contactid, long addressid, String email, String phone);
	
	int delete(long contactid);
	
	int deleteByAddressID(long addressid);
}
