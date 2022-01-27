package com.spring_boot.person_registry.term;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import com.spring_boot.person_registry.modell.Address;
import com.spring_boot.person_registry.modell.Contact;
import com.spring_boot.person_registry.modell.Person;
import com.spring_boot.person_registry.services.impl.AddressService;
import com.spring_boot.person_registry.services.impl.ContactService;
import com.spring_boot.person_registry.services.impl.PersonService;

class Delete {
	private PersonService pS;
	private AddressService aS;
	private ContactService cS;

	public Delete(PersonService pS, AddressService aS, ContactService cS) {
		this.pS = pS;
		this.aS = aS;
		this.cS = cS;
	}

	public void load(BufferedReader reader) {
		String cLine = null;
		try {
			do {
				out.print(Question.WITCH_TABLE_DELETE.toString());
				cLine = reader.readLine();
				
				if (cLine.replaceAll("[ ]|\t", "").length() > 0) {
					table(cLine.replaceAll("[ ]|\t", ""), reader);
				} else out.println(Error.SPACE_OR_TAB.toString());
			} while(!cLine.equals("end"));		
		} catch (IOException e) { }
	}
	
	private void table(String temp, BufferedReader reader) {
		switch (temp) {
			case "person": case "személyek": tdPerson(reader); break;
			case "address": case "címek": tdAddress(reader); break;
			case "contact": case "elérhetőségek": tdContact(reader); break;
		}
	}
	
	private void tdPerson(BufferedReader reader) {
		String cLine;
		try {
			out.print("\nKérem a személy nevét!\n");
			do {
				out.print("> ");
				cLine = reader.readLine();
				
				if (!cLine.replaceAll("[ ]|\t", "").equals("end")) {
					if (cLine.replaceAll("[ ]|\t", "").length() > 0) {
						Person pTemp = pS.findAll().get(pS.select(cLine));

						if (Objects.isNull(pTemp)) {
							List<Address> aTemp = new ArrayList<Address>();
							List<Contact> cTemp = new ArrayList<Contact>();
							
							for (Entry<Long, Address> a : aS.findAll().entrySet()) {
								if (a.getValue().getPersonid() == pTemp.getPersonid()) {
									aTemp.add(a.getValue());
								}
							}
							
							aTemp.forEach((v) -> {
								for (Entry<Long, Contact> c : cS.findAll().entrySet()) {
									if (c.getValue().getAddressid() == v.getAddressid()) {
										cTemp.add(c.getValue());
									}
								}
							});
							
							try {
								cTemp.forEach((v) -> cS.deleteByAddressID(v.getAddressid()));
							} catch (Exception e1) {
								out.print("\nA 'contact' tábla nem tartalmaz adatokat\n");
							}
							
							try {
								aS.deleteByPersonID(pTemp.getPersonid());
							} catch (Exception e1) {
								out.print("\nAz 'address' tábla nem tartalmaz adatokat\n");
							}
							
							pS.delete(pTemp.getPersonid());
						} else out.println("\nNem létezik a személy!\n");
					} else cLine = "end";
				}
			} while(!cLine.equals("end"));
		} catch (IOException e) { }
	}
	
	private void tdAddress(BufferedReader reader) {
		out.println("Ameddig a változó nem üres addig müködni fog!\n");
		String cLine;
		
		try {
			out.print("\nKérem a címet!\n");
			do {
				out.print("> ");
				cLine = reader.readLine();
				if (!cLine.replaceAll("[ ]|\t", "").equals("end")) {
					if (cLine.replaceAll("[ ]|\t", "").length() > 0) {
						Address aTemp = aS.findAll().get(aS.select(cLine));
						if (!Objects.isNull(aTemp)) {
							List<Contact> cTemp = new ArrayList<Contact>();
							
							for (Entry<Long, Contact> c : cS.findAll().entrySet()) {
								if (c.getValue().getAddressid() == aTemp.getAddressid()) {
									cTemp.add(c.getValue());
								}
							}
							
							try {
								cTemp.forEach((v) -> cS.deleteByAddressID(v.getAddressid()));
							} catch (Exception e1) {
								out.print("\nA 'contact' tábla nem tartalmaz adatokat\n");
							}
							aS.delete(aTemp.getAddressid());
						} else out.println("\nNem létezik a cím!\n");
					} else cLine = "end";
				}
			} while(!cLine.equals("end"));
		} catch (IOException e) { }
	}
	
	private void tdContact(BufferedReader reader) {
		out.println("Ameddig a változó nem üres addig müködni fog!\n");
		String cLine;
		try {
			do {
				out.print("elérhetőségek (email;phone)=> ");
				cLine = reader.readLine();
				
				if (cLine.replaceAll("[ ]|\t", "").length() > 0 && cLine.split("[ ]|[;]").length > 1) {
					Contact tcTemp = Contact.builder().email(cLine.split("[ ]|[;]")[0]).phone(cLine.split("[ ]|[;]")[1]).build();
					if (tcTemp.getEmail() != null || tcTemp.getPhone() != null) {
						cS.delete(
							cS.findAll().get(
								cS.select(tcTemp.getEmail(), tcTemp.getPhone())
							).getContactid()
						);
					} else out.print("Az 'email'-nak vagy a 'phone'-nak értéket kell adni!");
				} else cLine = "end";
			} while(!cLine.equals("end"));
		} catch (IOException e) { }
	}
}