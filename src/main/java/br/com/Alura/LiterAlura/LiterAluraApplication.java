package br.com.Alura.LiterAlura;

import br.com.Alura.LiterAlura.principal.Principal;
import br.com.Alura.LiterAlura.repository.IAutorRepository;
import br.com.Alura.LiterAlura.repository.ILivroRepository;
import br.com.Alura.LiterAlura.service.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {


	@Autowired
	ILivroRepository livroRepository;

	@Autowired
	IAutorRepository autorRepository;

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.exibeMenu();
	}
}
