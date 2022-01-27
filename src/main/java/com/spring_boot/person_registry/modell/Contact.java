package com.spring_boot.person_registry.modell;


public class Contact {
	private long contactid;
	private long addressid;
	private String email;
	private String phone;
	
	public static ContactBuilder builder() {
		return new ContactBuilder();
	}
	
	public static class ContactBuilder {
		private long contactid;
		private long addressid;
		private String email;
		private String phone;
		
		public ContactBuilder contactid(long value) {
			if (value > 0) {
				addressid = value;
			} else addressid = -1;
			return this;
		}
		
		public ContactBuilder addressid(long value) {
			if (value > 0) {
				addressid = value;
			} else addressid = -1;
			return this;
		}
		
		public ContactBuilder email(String value) {
			if (value.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:"+
					"\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"+
					"\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-"+
					"\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|"+
					"\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@"+
					"(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"+
					"[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25"+
					"[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"+
					"(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|"+
					"[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b"+
					"\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|"+
					"\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
			) {
				email = value;
			} else email = null;
			return this;
		}
		
		public ContactBuilder phone(String value) {
			if (value.matches(
					"^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]"+
					"?\\d{4}$|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$|^(\\+\\d{"+
					"1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
			) {
				phone = value;
			} else phone = null;
			return this;
		}
		
		public Contact build() {
			return new Contact(contactid, addressid, email, phone);
		}
	}
	
	public long getContactid() {
		return contactid;
	}

	public long getAddressid() {
		return addressid;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
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
		return "Contact { contactid: "+contactid+", addressid: "+ addressid +", email: "+ email +", phone: "+ phone +" }";
	}
	
	public boolean isNull(String value) {
		return value.replaceAll("[ ]|\t", "").length() > 0;
	}
	
	Contact(long contactid, long addressid, String email, String phone) {
		this.contactid = contactid;
		this.addressid = addressid;
		this.email = email;
		this.phone = phone;
	}
}

/*
 * value.matches(...) => https://emailregex.com/ && https://www.baeldung.com/java-regex-validate-phone-numbers
 */