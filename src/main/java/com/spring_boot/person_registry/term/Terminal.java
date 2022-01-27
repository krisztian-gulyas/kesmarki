package com.spring_boot.person_registry.term;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring_boot.person_registry.services.impl.AddressService;
import com.spring_boot.person_registry.services.impl.ContactService;
import com.spring_boot.person_registry.services.impl.PersonService;

@Component
public class Terminal {
	private final String tab = "    ";
	private final String answer = "> ";
	
	@Autowired PersonService pS;
	@Autowired AddressService aS;
	@Autowired ContactService cS;
	
	public void init() {
		;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String a = null;
			
			do {
				out.print(Question.FIRSTH_QUEST.toString());
					a = reader.readLine();
					
					if (a.replaceAll("[ ]|\t", "").length() < 1) { 
						out.println(Error.SPACE_OR_TAB.toString());
					} else if (Objects.isNull(a.replaceAll("[ ]|\t", ""))) { 
						a = "end"; 
					} else a = task(a, reader);
			} while(!a.equals("end"));
			
			reader.close();
		} catch (IOException e) {
			out.print(e);
		}
		out.println("\n"+message("stop")+"\n");
	}
	
	private String task(String a, BufferedReader reader) {
		switch (a.toString()) {
			case "1": case "1.)": case "Adatok lekérése": case "1.) Adatok lekérése": 
				new Querry(pS, aS, cS).load(reader); 
				break;
				
			case "2": case "2.)": case "Adatok rögzítése": case "2.) Adatok rögzítése": 
				new Insert(pS, aS, cS).load(reader); 
				break;
				
			case "3": case "3.)": case "Adatok modosítása": case "3.) Adatok modosítása": 
				new Modify(pS, aS, cS).load(reader);
				break;
			
			case "4": case "4.)": case "Adatok törlése": case "4.) Adatok törlése": 
				new Delete(pS, aS, cS).load(reader); 
				break;
			
			default: if (!a.equals("end")) out.println(Error.IS_NOT_EXISTS_THE_ANSWER_OPTION.toString());
		}
		
		return a;
	}
	
	private String message(String element) {
		switch (element) {			
			case "selectOther": return "Egy vagy több táblát kell majd lekérdeznem?\n" + answer;
			case "selectTable": return "Melyik táblát? [person / személyek ; address / címek ; contact / elérhetőségek]\n" + answer;
			case "select": return "Tábla lekérdezése:\n"+ tab +"Milyik táblát kérdezzem le?\n" + answer;
			
			case "": return "";
			
			case "stop": return "Ön most befejezi a müveleteket, ezért aprogram leáll!";
		}
		return null;
	}
}