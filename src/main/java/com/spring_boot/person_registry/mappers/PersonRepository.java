package com.spring_boot.person_registry.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.spring_boot.person_registry.modell.Person;

@Mapper
public interface PersonRepository {
	@Select("SELECT * FROM person")
	@MapKey("personid")
	Map<Long, Person> findAll();
	
	@Select("SELECT personid FROM person WHERE name=#{name}")
	long select(String name);
	
	@Select("SELECT (CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END) FROM person WHERE name=#{name}")
	boolean isExist(String name);
	
	@Insert("INSERT INTO person(name) VALUES(#{name})")
	int insert(String name);
	
	@Update("UPDATE person SET name=#{name} WHERE personid=#{personid}")
	int update(long personid, String name);
	
	@Delete("DELETE FROM person Where personid=#{personid}")
	int delete(long personid);
}