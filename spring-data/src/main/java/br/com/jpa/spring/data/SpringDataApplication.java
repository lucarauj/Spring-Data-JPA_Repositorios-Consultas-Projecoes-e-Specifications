package br.com.jpa.spring.data;

import br.com.jpa.spring.data.orm.Cargo;
import br.com.jpa.spring.data.service.CargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    private Boolean system = true;

	private final CargoService service;

	public SpringDataApplication(CargoService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("ESCOLHA UMA OPÇÃO:");
			System.out.println("0 - Sair");
			System.out.println("1 - Opções");

			int action = scanner.nextInt();

			if(action == 1) {
				service.inicial(scanner);
			} else {
				system = false;
			}
        }
	}
}
