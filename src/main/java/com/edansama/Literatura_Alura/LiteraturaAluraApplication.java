package com.edansama.Literatura_Alura;

import com.edansama.Literatura_Alura.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class LiteraturaAluraApplication implements CommandLineRunner {

	@Autowired
	private Main main;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.showMenu();
	}
}
