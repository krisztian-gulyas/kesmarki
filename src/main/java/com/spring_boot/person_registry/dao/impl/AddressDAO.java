package com.spring_boot.person_registry.dao.impl;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring_boot.person_registry.modell.Address;

public interface AddressDAO {
	Map<Long, Address> findAll();
	
	boolean isExist(String address);
	
	long select(String address);
	
	Address[] selectByPersonID(long personid);
	
	int insert(long personid, String address);
	
	int update(long addressid, long personid, String address);
	
	int delete(long addressid);
	
	int deleteByPersonID(long personid);
}
