package com.spring_boot.person_registry.term;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import com.spring_boot.person_registry.modell.Address;
import com.spring_boot.person_registry.modell.Contact;
import com.spring_boot.person_registry.modell.Person;
import com.spring_boot.person_registry.services.impl.AddressService;
import com.spring_boot.person_registry.services.impl.ContactService;
import com.spring_boot.person_registry.services.impl.PersonService;

class Insert {
	private PersonService pS;
	private AddressService aS;
	private ContactService cS;
	

	public Insert(PersonService pS, AddressService aS, ContactService cS) {
		this.pS = pS;
		this.aS = aS;
		this.cS = cS;
	}

	public void load(BufferedReader reader) {
		try {
			String cLine = null;
			
			do {
				out.print(Question.WITCH_TABLE_INSERT.toString());
				cLine = reader.readLine();
				
				if (cLine.replaceAll("[ ]|\t", "").length() < 1) { 
					out.println(Error.SPACE_OR_TAB.toString());
				} else table(cLine.replaceAll("[ ]|\t", "").toString(), reader);
			} while (!cLine.equals("end"));
		} catch (IOException e) { }
	}
	
	private void table(String temp, BufferedReader reader) {
		switch (temp) {
			case "person": case "személyek": tiPerson(reader); break;
			case "address": case "címek": tiAddress(reader); break;
			case "contact": case "elérhetőségek": tiContact(reader); break;
			default: if (!temp.equals("end")) out.print(Error.IS_NOT_EXISTS_THE_ANSWER_OPTION.toString());
		}
	}
	
	private void tiPerson(BufferedReader reader) {
		try {
			out.println("\nAddig ismétlődik amíg az érték nem nulla!\nKérem a személy teljesnevét akit a táblába kíván felvinni!");
			String i = null;
			do {
				out.print("> ");
				i = reader.readLine();
				
				if (Objects.isNull(i)) return;
				
				if (!Objects.isNull(Person.builder().name(i).build().getName())) {
					if (!i.replaceAll("[ ]|\t", "").equals("end")) {
						if (!pS.isExist(i)) {
							pS.insert(i);
						} else out.println(i + " már szerepel egyszer a 'person' táblában!");
					}
				} else out.print(Error.SPACE_OR_TAB.toString());
			} while(!i.replaceAll("[ ]|\t", "").equals("end"));
		} catch (IOException e) { }
	}
	
	private void tiAddress(BufferedReader reader) {
		try {
			out.print("\nAddig ismétlődik amíg az érték nem nulla vagy nem 'end'!\nKérem a személy teljesnevét akihez a címet vagy címeket felszeretné vinni!\n> ");
			String i = reader.readLine(), o = null;
		
			if (Objects.isNull(i) || i.replaceAll("[ ]|\t", "").equals("end")) return;
			
			Person tP = null;
			
			try {
				tP = pS.findAll().get(pS.select(i));
				
				out.print("\nAdja meg '"+ i +"' címét!\n");
				
				do {
					out.print("> ");
					o = reader.readLine();
					
					Address tA = Address.builder().personid(tP.getPersonid()).address(o).build();
					if (!o.replaceAll("[ ]|\t", "").equals("end")) {
						if (!Objects.isNull(tA.getAddress())) {
							if (aS.isExist(tA.getAddress())) {
								if (aS.findAll().get(aS.select(tA.getAddress())).getPersonid() != tA.getPersonid()) {
									aS.insert(tA.getPersonid(), tA.getAddress());
								} else out.println(i + "-nek/nak már rögzítve lett egyszer az 'address' táblában a címe!");
							} else aS.insert(tA.getPersonid(), tA.getAddress());
						} else out.print(Error.SPACE_OR_TAB.toString());
					}
				} while(!o.replaceAll("[ ]|\t", "").equals("end"));
			} catch (Exception e) {
				out.println("\nNem létezik a személy!\n");
			}
		} catch (IOException e) {}
	}
	
	private void tiContact(BufferedReader reader) {
		try {
			out.print("\nKérem a személy teljesnevét, hogy letudjam kérni a címét/címeit!\n> ");
			String i = reader.readLine(), e, o = null;
			
			if (Objects.isNull(i) || i.replaceAll("[ ]|\t", "").equals("end")) return;
			
			try {
				Person tP = pS.findAll().get(pS.select(i));

				try {
					Address[] atA = aS.selectByPersonID(tP.getPersonid());
					
					out.print("\n--------------------\n");
					for (Address a : atA) {
						out.println(a.toString());
					}
					out.print("--------------------\n");
					
					out.print("\nKérem az 'addressid'-t, amihez elérhetőséget/veléhetőségeket kell felvinni vinni!\n> ");
					e = reader.readLine();
					
					try {
						Address a = aS.findAll().get(Long.parseLong(e));
						
						if (!Objects.isNull(a)) {
							out.print("\nAddig ismétlődik amíg az érték nem nulla vagy nem 'end'!\nKérem az elérhetőségeket! [(email phone)] \n");
							do {
								out.print("> ");
								o = reader.readLine();
								
								if (!o.replaceAll("[ ]|\t", "").equals("end")) {
									String[] contact = o.split(" ");
									
									if (contact.length == 2) {
										Contact tC = Contact.builder().addressid(a.getAddressid()).email(contact[1]).phone(contact[2]).build();
										cS.insert(tC.getAddressid(), tC.getEmail(), tC.getPhone());
									} else out.println("\nBiztos, hogy ' ' választotta el az adatokat?\n");
								}
							} while(o != null);
						} else out.println("\nNem létezik a cím!\n");
					} catch (NumberFormatException e3) {
						out.print("Nem megfelelő formátumot adott meg!");
					}
				} catch (Exception e2) {
					out.println(e2+"\nA személyhez nem lett hozzárendelve cím!\n");
				}
			} catch (Exception e1) {
				out.println("\nNem létezik a személy!\n");
			}
		} catch (IOException e0) { }
	}
}

// 224 Belmont Street APT 220
// alpha.ahpla@gmail.com
// +36301231234