package com.spring_boot.person_registry.term;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.spring_boot.person_registry.modell.Address;
import com.spring_boot.person_registry.modell.Contact;
import com.spring_boot.person_registry.modell.Person;
import com.spring_boot.person_registry.services.impl.AddressService;
import com.spring_boot.person_registry.services.impl.ContactService;
import com.spring_boot.person_registry.services.impl.PersonService;

@SuppressWarnings("unlikely-arg-type")
class Querry {
	private PersonService pS;
	private AddressService aS;
	private ContactService cS;
	
	public Querry(PersonService pS, AddressService aS, ContactService cS) {
		this.pS = pS;
		this.aS = aS;
		this.cS = cS;
	}
	
	public void load(BufferedReader reader) {
		String cLine = null;
		try {
			do {
				out.print(Question.WITCH_TABLE_QUERRY.toString());
				cLine = reader.readLine();
				
				if (cLine.replaceAll("[ ]|\t", "").length() > 0) {						
					table(cLine, reader);
					if (cLine.equals("end")) cLine = null; 
				} else out.print(Error.SPACE_OR_TAB.toString());
			} while(cLine != null);
		} catch (IOException e) { }
	}
	
	private void table(String temp, BufferedReader reader) {
		switch (temp) {
			case "person": case "személyek": tqPerson(reader); break;
			case "address": case "címek": tqAddress(reader); break;
			case "contact": case "elérhetőségek": tqContact(reader); break;
			default: if (!temp.equals("end")) out.println(Error.IS_NOT_EXISTS_THE_ANSWER_OPTION.toString());
		}
	}
	
	private void tqPerson(BufferedReader reader) {
		try {
			out.print("\n<where ('all' | 'name')> (name: value)\n> ");
			String c = reader.readLine();
			if (Objects.isNull(c) || c.replaceAll("[ ]|\t", "").equals("end")) return;
			
			String[] at = c.split(" ");
			
			switch (at[0]) {
				case "all": /* Lekérdezi a választott tábla minden adatát és kiírja! */
					Map<Long, Person> temp = pS.findAll();
					if (temp.size() > 0) {
						out.println("--------------------\n");
						temp.forEach((k, v) -> out.println(v.toString()));
						out.println("--------------------\n");
					} else out.println("\nA tábla nem tartalmaz rekortokat!\n");
					break;
					
				case "name": /* Lekérdezi a választott táblé a megadott value alapján! */
					try {
						if (at[1].replaceAll("[ ]|\t", "").length() < 1) { 
							out.println(Error.SPACE_OR_TAB.toString());
						} else out.println(pS.findAll().get(pS.select(at[1])).toString());
					} catch (Exception e) {
						out.println(Error.IS_NULL_VALUE.toString());
					}
					break;
					
				default: out.print(Error.IS_NOT_EXISTS_THE_COLUMN.toString());
			}
		} catch (IOException e) { }
	}
	
	private void tqAddress(BufferedReader reader) {
		try {
			out.print("\n<where ('all' | 'address') >>> ");
			String c = reader.readLine();
			if (Objects.isNull(c) || c.replaceAll("[ ]|\t", "").equals("end")) return;
			
			out.print("address >>> ");
			String t = reader.readLine();
			if (Objects.isNull(t) || t.replaceAll("[ ]|\t", "").equals("end")) return;
			
			switch (c) {
				case "all": /* Lekérdezi a választott tábla minden adatát és kiírja! */
					Map<Long, Address> temp = aS.findAll();
					if (temp.size() > 0) {
						temp.forEach((k, v) -> out.println(v.toString()));
					} else out.println("\nA tábla nem tartalmaz rekortokat!\n");
					break;
					
				case "address":
					try {
						if (t.replaceAll("[ ]|\t", "").length() < 1) { 
							out.println(Error.SPACE_OR_TAB.toString());
						} else {
							Address a = aS.findAll().get(aS.select(t));
							Person p = pS.findAll().get(a.getPersonid());
							out.println("\nAddress { addressid: "+ a.getAddressid() +", "+ p.toString() +", address: "+ a.getAddress() +" }");
						}
					} catch (Exception e) {
						out.println(Error.IS_NULL_VALUE.toString());
					}
					break;
					
				default: out.print(Error.IS_NOT_EXISTS_THE_COLUMN.toString());
			}
		} catch (IOException e) { }
	}
	
	private void tqContact(BufferedReader reader) {
		try {
			out.print("\n<where ('all' | 'email &| phone')> [(email: value);(phone: value)]\n> ");
			String c = reader.readLine();
			if (Objects.isNull(c) || c.replaceAll("[ ]|\t", "").equals("end")) return;
			
			String[] at = c.split(" ");
			
			switch (at[0]) {
				case "all": /* Lekérdezi a választott tábla minden adatát és kiírja! */
					Map<Long, Contact> temp = cS.findAll();
					if (temp.size() > 0) {
						temp.forEach((k, v) -> out.println(v.toString()));
					} else out.println("\nA tábla nem tartalmaz rekortokat!\n");
					break;
					
				case "email &| phone":
					try {
						if (at[1].replaceAll("[ ]|\t", "").length() < 1) { 
							out.println(Error.SPACE_OR_TAB.toString());
						} else {
							String[] att = at[1].split(";");
							
							Contact tC = cS.findAll().get(cS.select(att[0], att[1]));
							Address a = aS.findAll().get(tC.getAddressid());
							Person p = pS.findAll().get(a.getPersonid());
							
							out.print(
								p.toString() + "\n\t" + a.toString() + "\n\t\t"+ tC.toString()
							);
						}
					} catch (Exception e) {
						out.println("\nA tábla kilett választva, de a keresett érték az nem lett megadva!\n");
					}
					break;
					
				default: out.print(Error.IS_NOT_EXISTS_THE_COLUMN.toString());
			}
		} catch (IOException e) { }
	}
}