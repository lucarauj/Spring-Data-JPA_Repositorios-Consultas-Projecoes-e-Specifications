package br.com.jpa.spring.data;

import br.com.jpa.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    private Boolean system = true;

	private final CargoService cargoService;

	private final FuncionarioService funcionarioService;

	private final UnidadeTrabalhoService unidadeTrabalhoService;

	private final RelatorioService relatorioService;

	public final RelatorioFuncionarioDinamico funcionarioDinamico;

	public SpringDataApplication(
			CargoService cargoService,
			FuncionarioService funcionarioService,
			UnidadeTrabalhoService unidadeTrabalhoService,
			RelatorioService relatorioService,
			RelatorioFuncionarioDinamico funcionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.funcionarioDinamico = funcionarioDinamico;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("\nESCOLHA UMA OPÇÃO:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatório");
			System.out.println("5 - Relatório dinâmico");

			Integer action = scanner.nextInt();

			switch (action) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatorioService.inicial(scanner);
					break;
				case 5:
					funcionarioDinamico.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
        }
	}
}
