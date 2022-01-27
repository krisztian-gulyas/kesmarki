package com.spring_boot.person_registry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring_boot.person_registry.dao.*;
import com.spring_boot.person_registry.dao.impl.*;
import com.spring_boot.person_registry.services.*;
import com.spring_boot.person_registry.services.impl.*;

@Configuration
public class Config {
	/* Data Access Object */
	@Bean public PersonDAO personDAO() {
		return new PersonDAOImpl();
	}
	
	@Bean public AddressDAO addressDAO() {
		return new AddressDAOImpl();
	}
	
	@Bean public ContactDAO contactDAO() {
		return new ContactDAOImpl();
	}
	
	/* Services */
	@Bean public PersonService personService() {
		return new PersonServiceImpl();
	}
	
	@Bean public AddressService addressService() {
		return new AddressServiceImpl();
	}
	
	@Bean public ContactService contactService() {
		return new ContactServiceImpl();
	}
}
