package com.spring_boot.person_registry.modell;

public class Address {
	private long addressid;
	private long personid;
	private String address;
	
	public static AddressBuilder builder() {
		return new AddressBuilder();
	}
	
	public static class AddressBuilder {
		private long addressid;
		private long personid;
		private String address;
		
		public AddressBuilder addressid(long value) {
			if (value > 0) {
				addressid = value;
			} else addressid = -1;
			return this;
		}
		
		public AddressBuilder personid(long value) {
			if (value > 0) {
				personid = value;
			} else personid = -1;
			return this;
		}
		
		public AddressBuilder address(String value) {
			if (value.matches("^(\\d+)?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\\d*)?$")) {
				address = value;
			} else address = null;
			return this;
		}
		
		public Address build() {
			return new Address(addressid, personid, address);
		}
	}
	
	public long getAddressid() {
		return addressid;
	}

	public long getPersonid() {
		return personid;
	}

	public String getAddress() {
		return address;
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
		return "Address { addressid: "+addressid+", personid: "+ personid +", address: "+ address +" }";
	}
	
	public boolean isNull(String value) {
		return value.replaceAll("[ ]|\t", "").length() > 0;
	}
	
	Address(long addressid, long personid, String address) {
		this.addressid = addressid;
		this.personid = personid;
		this.address = address;
	}
}


/*
 * value.matches(...) => https://community.alteryx.com/t5/Alteryx-Designer-Discussions/RegEx-Addresses-different-formats-and-headaches/td-p/360147
 */