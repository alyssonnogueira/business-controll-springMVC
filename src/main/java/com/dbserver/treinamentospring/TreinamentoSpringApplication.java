package com.dbserver.treinamentospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class TreinamentoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoSpringApplication.class, args);
	}

}
