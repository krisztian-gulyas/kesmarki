package com.spring_boot.person_registry.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.AddressDAO;
import com.spring_boot.person_registry.mappers.AddressRepository;
import com.spring_boot.person_registry.modell.Address;

public class AddressDAOImpl implements AddressDAO {
	@Autowired AddressRepository mapper;
	
	@Override
	public Map<Long, Address> findAll() {
		return mapper.findAll();
	}
	
	@Override
	public boolean isExist(String address) {
		return mapper.isExist(address);
	}
	
	@Override
	public long select(String address) {
		return mapper.select(address);
	}
	
	@Override
	public Address[] selectByPersonID(long personid) {
		return mapper.selectByPersonID(personid);
	}
	
	@Override
	public int insert(long personid, String address) {
		return mapper.insert(personid, address);
	}
	
	@Override
	public int update(long addressid, long personid, String address) {
		return mapper.update(addressid, personid, address);
	}
	
	@Override
	public int deleteByPersonID(long personid) {
		return mapper.deleteByPersonID(personid);
	}
	
	@Override
	public int delete(long addressid) {
		return mapper.delete(addressid);
	}
}