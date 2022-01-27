package com.spring_boot.person_registry.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.ContactDAO;
import com.spring_boot.person_registry.mappers.ContactRepository;
import com.spring_boot.person_registry.modell.Contact;

public class ContactDAOImpl implements ContactDAO {
	@Autowired ContactRepository mapper;
	
	@Override
	public Map<Long, Contact> findAll() {
		return mapper.findAll();
	}
	
	@Override
	public boolean isExist(String email, String phone) {
		return mapper.isExist(email);
	}
	
	@Override
	public long select(String email, String phone) {
		return mapper.select(email, phone);
	}
	
	@Override
	public Contact[] selectByAddressID(long addressid) {
		return mapper.selectByAddressID(addressid);
	}
	
	@Override
	public int insert(long addressid, String email, String phone) {
		return mapper.insert(addressid, phone);
	}
	
	@Override
	public int update(long contactid, long addressid, String email, String phone) {
		return mapper.update(addressid, addressid, phone);
	}
	
	@Override
	public int delete(long contactid) {
		return mapper.delete(contactid);
	}
	
	@Override
	public int deleteByAddressID(long addressid) {
		return mapper.deleteByAddressID(addressid);
	}
}