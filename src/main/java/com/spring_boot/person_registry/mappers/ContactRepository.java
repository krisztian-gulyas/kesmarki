package com.spring_boot.person_registry.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.spring_boot.person_registry.modell.Contact;

@Mapper
public interface ContactRepository {
	@Select("SELECT * FROM contact")
	@MapKey("contactid")
	Map<Long, Contact> findAll();
	
	@Select({
		"<script>"+
			"SELECT contactid FROM contact "+
			"<where>"+
				"<if test='email!=null'>email=#{email}</if>"+
				"<if test='phone!=null'><if test='email!=null'>,</if>phone=#{phone}</if>"+
			"</where>"+
		"</script>"
	})
	long select(String email, String phone);
	
	@Select("SELECT * FROM contact WHERE addressid=#{addressid}")
	Contact[] selectByAddressID(long addressid);
	
	@Select("SELECT (CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END) FROM contact WHERE email=#{email}")
	boolean isExist(String email);
	
	@Insert("INSERT INTO address(addressid, address) VALUES(#{addressid}, #{address})")
	int insert(long addressid, String address);
	
	@Update({
		"<script>"+
			"UPDATE account "+
				"<set>"+
					"<if test='personid > 0'>personid=#{personid}</if>"+
					"<if test='address != null'><if test='personid > 0'>,</if>address=#{address}</if>"+
				"</set>"+
			"WHERE accountid=#{accountid}"+
		"</script>"
	})
	int update(long addressid, long personid, String address);
	
	@Delete("DELETE FROM contact WHERE addressid=#{addressid}")
	int delete(long addressid);
	
	@Delete("DELETE FROM contact WHERE personid=#{personid}")
	int deleteByAddressID(long personid);
}