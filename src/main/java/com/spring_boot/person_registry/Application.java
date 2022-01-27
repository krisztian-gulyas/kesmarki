package com.spring_boot.person_registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring_boot.person_registry.term.Terminal;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired Terminal terminal;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) {
		terminal.init();
	}
}