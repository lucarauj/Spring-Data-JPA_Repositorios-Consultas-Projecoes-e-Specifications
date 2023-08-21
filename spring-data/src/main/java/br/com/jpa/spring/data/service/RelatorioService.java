package br.com.jpa.spring.data.service;

import br.com.jpa.spring.data.orm.Funcionario;
import br.com.jpa.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("\nESCOLHA UMA OPÇÃO:");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionários por nome");
            System.out.println("2 - Buscar funcionários por nome, data contratação e salário maior");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscarPorNome(scanner);
                    break;
                case 2:
                    buscarPorNomeDataContratacaoSalario(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    private void buscarPorNome(Scanner scanner) {
        System.out.println("Nome do funcionário:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        List<Funcionario> funcionarioList = funcionarioRepository.findByNomeContainingIgnoreCase(nome);
        if(!funcionarioList.isEmpty()) {
            for (Funcionario funcionario : funcionarioList) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Nome não encontrado!");
        }
    }

    private void buscarPorNomeDataContratacaoSalario(Scanner scanner) {
        System.out.println("Nome do funcionário:");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.println("Salário:");
        Double salario = scanner.nextDouble();

        System.out.println("Data de contratação (dia/mês/ano):");
        LocalDate dataContratacao = LocalDate.parse(scanner.next(), formatter);

        List<Funcionario> funcionarioList = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataContratacao);
        if(!funcionarioList.isEmpty()) {
            for (Funcionario funcionario : funcionarioList) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }
}
