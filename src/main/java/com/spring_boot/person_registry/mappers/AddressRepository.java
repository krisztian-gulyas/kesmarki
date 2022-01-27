package com.spring_boot.person_registry.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.spring_boot.person_registry.modell.Address;

@Mapper
public interface AddressRepository {
	@Select("SELECT * FROM address")
	@MapKey("addressid")
	Map<Long, Address> findAll();
	
	@Select("SELECT addressid FROM address WHERE address=#{address}")
	long select(String address);
	
	@Select("SELECT * FROM address WHERE personid=#{personid}")
	Address[] selectByPersonID(long personid);
	
	@Select("SELECT (CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END) FROM address WHERE address=#{address}")
	boolean isExist(String address);
	
	@Insert("INSERT INTO address(personid, address) VALUES(#{personid}, #{address})")
	int insert( long personid, String address);
	
	@Update({
		"<script>"+
			"UPDATE address "+
				"<set>"+
					"<if test='personid > 0'>personid=#{personid}</if>"+
					"<if test='address != null'><if test='personid > 0'>,</if>address=#{address}</if>"+
				"</set>"+
			"WHERE addressid=#{addressid}"+
		"</script>"
	})
	int update(long addressid, long personid, String address);
	
	@Delete("DELETE FROM address WHERE addressid=#{addressid}")
	int delete(long addressid);
	
	@Delete("DELETE FROM address WHERE personid=#{personid}")
	int deleteByPersonID(long personid);
}