package com.spring_boot.person_registry.modell;

public class Person {
	private long personid;
	private String name;
	
	public static PersonBuilder builder() {
		return new PersonBuilder();
	}
	
	public static class PersonBuilder {
		private long personid;
		private String name;
		
		public PersonBuilder personid(long value) {
			if (value > 0) {
				personid = value;
			} else personid = -1;
			return this;
		}
		
		public PersonBuilder name(String value) {
			if (value.replaceAll("[ ]|\t", "").length() > 0) {
				name = value;
			} else name = null;
			return this;
		}
		
		public Person build() {
			return new Person(personid, name);
		}
	}
	
	public long getPersonid() {
		return personid;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return "Person { personid: "+ personid +", name: "+ name +" }";
	}
	
	Person(long personid, String name) {
		this.personid = personid;
		this.name = name;
	}
}