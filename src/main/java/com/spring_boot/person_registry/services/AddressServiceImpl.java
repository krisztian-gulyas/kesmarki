package com.spring_boot.person_registry.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.AddressDAO;
import com.spring_boot.person_registry.modell.Address;
import com.spring_boot.person_registry.services.impl.AddressService;

public class AddressServiceImpl implements AddressService {
	@Autowired AddressDAO addressDAO;
	
	@Override
	public Map<Long, Address> findAll() {
		return addressDAO.findAll();
	}
	
	@Override
	public boolean isExist(String address) {
		return addressDAO.isExist(address);
	}
	
	@Override
	public long select(String address) {
		return addressDAO.select(address);
	}
	
	@Override
	public Address[] selectByPersonID(long personid) {
		return addressDAO.selectByPersonID(personid);
	}
	
	@Override
	public int insert(long personid, String address) {
		return addressDAO.insert(personid, address);
	}
	
	@Override
	public int update(long addressid, long personid, String address) {
		return addressDAO.update(addressid, personid, address);
	}
	
	@Override
	public int deleteByPersonID(long personid) {
		return addressDAO.deleteByPersonID(personid);
	}
	
	@Override
	public int delete(long addressid) {
		return addressDAO.delete(addressid);
	}
}