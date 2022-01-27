package com.spring_boot.person_registry.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring_boot.person_registry.dao.impl.ContactDAO;
import com.spring_boot.person_registry.modell.Contact;
import com.spring_boot.person_registry.services.impl.ContactService;

public class ContactServiceImpl implements ContactService {
	@Autowired ContactDAO contactDAO;
	
	@Override
	public Map<Long, Contact> findAll() {
		return contactDAO.findAll();
	}
	
	@Override
	public boolean isExist(String email, String phone) {
		return contactDAO.isExist(email, phone);
	}
	
	@Override
	public long select(String email, String phone) {
		return contactDAO.select(email, phone);
	}
	
	@Override
	public Contact[] selectByAddressID(long addressid) {
		return contactDAO.selectByAddressID(addressid);
	}
	
	@Override
	public int insert(long addressid, String email, String phone) {
		return contactDAO.insert(addressid, email, phone);
	}
	
	@Override
	public int update(long contactid, long addressid, String email, String phone) {
		return contactDAO.update(contactid, addressid, email, phone);
	}
	
	@Override
	public int delete(long contactid) {
		return contactDAO.delete(contactid);
	}
	
	@Override
	public int deleteByAddressID(long addressid) {
		// TODO Auto-generated method stub
		return contactDAO.deleteByAddressID(addressid);
	}
}