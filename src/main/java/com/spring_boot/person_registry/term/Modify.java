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

public class Modify {
	private PersonService pS;
	private AddressService aS;
	private ContactService cS;

	public Modify(PersonService pS, AddressService aS, ContactService cS) {
		this.pS = pS;
		this.aS = aS;
		this.cS = cS;
	}
	
	public void load(BufferedReader reader) {
		String cLine = null;
		try {
			do {
				out.print(Question.WITCH_TABLE_MODIFY.toString());
				cLine = reader.readLine();
				
				if (cLine.replaceAll("[ ]|\t", "").length() > 0) {
					table(cLine.replaceAll("[ ]|\t", ""), reader);
				} else out.println(Error.SPACE_OR_TAB.toString());
			} while(!cLine.equals("end"));
		} catch (IOException e) { }
	}
	
	private void table(String temp, BufferedReader reader) {
		switch (temp) {
			case "person": case "személyek": tmPerson(reader); break;
			case "address": case "címek": tmAddress(reader); break;
			case "contact": case "elérhetőségek": tmContact(reader); break;
			default: if (!temp.equals("end")) out.print(Error.IS_NOT_EXISTS_THE_ANSWER_OPTION.toString());
		}
	}
	
	private void tmPerson(BufferedReader reader) {
		String a = null, n;
		
		try {
			Map<Long, Person> mP;
			
			out.println("\nAddig ismétlődik amíg az érték nem nulla vagy nem 'end'!");
			
			do {
				mP = pS.findAll();
				
				if (mP.size() == 0) {
					out.println("A tábla nem tartalmaz rekordokat!\n");
					break;
				}
				
				out.println("\n--------------------");
				mP.forEach((k, v) -> out.println(v.toString()));
				out.println("--------------------\n");
				
				out.print("\nA kiírt elemek közül annak az personid-ját írja ide, amelyiket modosítaná!\n> ");
				a = reader.readLine();
				
				try {
					if (!a.replaceAll("[ ]|\t", "").equals("end")) {
						if (a.replaceAll("[ ]|\t", "").length() > 0) {
							long temp = Long.parseLong(a);
							Person p = mP.get(temp), p2;
		
							out.print("Változás:\n\t"+ p.getName() +" >>> ");
							n = reader.readLine();
							
							p2 = (
								Person.builder()
									.personid(p.getPersonid())
									.name(p.getName().equals(n) ? null : n)
								.build()
							);
							
							if (!Objects.isNull(p2.getName())) {
								if (pS.update(p2.getPersonid(), p2.getName()) > 0) {
									
								} else out.print("Nem sikerült a személyt frissíteni!");
							} else out.print("A két név azonos, nem változik semmi!");
						} else a = "end";
					}
				} catch (NumberFormatException e) {
					out.print("Nem megfelelő formátumot adott meg!");
				}
			} while (!a.equals("end"));
		} catch (IOException e) { }
	}
	
	private void tmAddress(BufferedReader reader) {
		String b = null, value, value2;
		
		try {
			Map<Long, Person> mP;
			out.println("\nAddig ismétlődik amíg az érték nem nulla vagy nem 'end'!");
			
			do {
				mP = pS.findAll();
				final Map<Long, Address> mA = aS.findAll();
				
				if (mA.size() == 0) {
					out.println("A tábla nem tartalmaz rekordokat!\n");
					break;
				}
				
				out.println("\n--------------------");
				mP.forEach(
					(k, v) -> {
						mA.forEach(
							(k1, v1) -> {
								if (v.getPersonid() == v1.getPersonid()) {
									out.println("Address { addressid: "+ v1.getAddressid() +", "+ v.toString() +", address: "+ v1.getAddress() +" }");
								}
							}
						);
					}
				);
				out.println("--------------------\n");
				
				out.println("\nA kiírt elemek közül annak az 'addressid'-ját írja ide, amelyiket modosítaná!\n> ");
				b = reader.readLine();
				
				try {
					if (!b.replaceAll("[ ]|\t", "").equals("end")) {
						if (b.replaceAll("[ ]|\t", "").length() > 0) {
							long temp = Long.parseLong(b), temp2 = -1, selectATemp = 0;
							Address a = mA.get(temp), a2;
							
							out.print("Változás:\n\t"+ a.getPersonid()+" | "+ mP.get(a.getPersonid()).getName() +" >>> ");
							value = reader.readLine();
							
							out.print("\t"+ a.getAddress() +" >>> ");
							value2 = reader.readLine();
							
							try { 
								temp2 = Long.parseLong(value); 
							} catch (NumberFormatException e) {
								try {
									selectATemp = aS.select(value);
								} catch (Exception e1) { }
							}
							
							a2 = (
								Address.builder()
									.addressid(a.getAddressid())
									.personid(temp2 > -1 ? temp2 : selectATemp)
									.address(a.getAddress().equals(value2) ? null : value2)
								.build()
							);
							
							if (a2.getPersonid() < 1) out.println((temp2 == -1 ? "\nA megadott személynév nem található meg a person táblában!\n" : "\nAz id értéke nem lehet kissebb, mint 1!\n"));
							
							try {
								aS.update(a2.getAddressid(), a2.getPersonid(), a2.getAddress());
							} catch (Exception e) {
								out.println("Az address táblában nem történt frissítés!");
							}
						} else b = "end";
					}
				} catch (NumberFormatException e) {
					out.print("Nem megfelelő formátumot adott meg!");
				}
			} while (!b.equals("end"));
		} catch (IOException e) { }
	}
	
	private void tmContact(BufferedReader reader) {
		String b = null, value, value2, value3;
		try {
			Map<Long, Person> mP;
			
			out.println("\nAddig ismétlődik amíg az érték nem nulla vagy nem 'end'!");
			
			do {
				mP = pS.findAll();
				final Map<Long, Address> mA = aS.findAll();
				final Map<Long, Contact> mC = cS.findAll();
				if (mA.size() == 0) {
					out.println("A tábla nem tartalmaz rekordokat!\n");
					break;
				}
				
				out.println("\n--------------------");
				mP.forEach(
					(k, v) -> {
						mA.forEach(
							(k1, v1) -> {
								mC.forEach(
									(k2, v2) -> {
										if (v.getPersonid() == v1.getPersonid() && v1.getAddressid() == v2.getAddressid()) {
											out.println("Contact { contactid: "+ v2.getContactid() +", Address { addressid: "+ v1.getAddressid() +", "+ v.toString() +", address: "+ v1.getAddress() +" }, email: "+ v2.getEmail() +", phone: "+ v2.getPhone() +" }");
										}
									}
								);
							}
						);
					}
				);
				out.println("--------------------\n");
				
				out.println("\nA kiírt elemek közül annak a 'contactid'-ját írja ide, amelyiket modosítaná!\n> ");
				b = reader.readLine();
				
				try {
					if (!b.replaceAll("[ ]|\t", "").equals("end")) {
						if (b.replaceAll("[ ]|\t", "").length() > 0) {
							long temp = Long.parseLong(b), temp2 = -1, selectCTemp = 0;
							Contact c = cS.findAll().get(temp), c2;
							
							out.print("Változás:\n\t"+ c.getAddressid()+" | "+ mA.get(c.getAddressid()).getAddress() +" >>> ");
							value = reader.readLine();
							
							out.print("\t"+ c.getEmail() +" >>> ");
							value2 = reader.readLine();
							
							out.print("\t"+ c.getPhone() +" >>> ");
							value3 = reader.readLine();
							
							try { 
								temp2 = Long.parseLong(value); 
							} catch (NumberFormatException e) {
								try {
									selectCTemp = aS.select(value);
								} catch (Exception e1) { }
							}
							
							c2 = (
								Contact.builder()
									.contactid(c.getContactid())
									.addressid(temp2 > -1 ? temp2 : selectCTemp)
									.email(value2)
									.phone(value3)
								.build()
							);
							
							if (c2.getAddressid() < 1) out.println((temp2 == -1 ? "\nA megadott cím nem található meg az 'address' táblában!\n" : "\nAz id értéke nem lehet kissebb, mint 1!\n"));
							
							try {
								cS.update(c2.getContactid(), c2.getAddressid(), c2.getEmail(), c2.getPhone());
							} catch (Exception e) {
								out.println("Az address táblában nem történt frissítés!");
							}
						} else b = "end";
					}
				} catch (NumberFormatException e) {
					out.print("Nem megfelelő formátumot adott meg!");
				}
			} while (!b.equals("end"));
		} catch (IOException e) { }
	}
}