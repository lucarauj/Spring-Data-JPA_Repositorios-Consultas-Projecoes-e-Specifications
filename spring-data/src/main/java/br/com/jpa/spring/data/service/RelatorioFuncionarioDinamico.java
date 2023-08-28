package br.com.jpa.spring.data.service;

import br.com.jpa.spring.data.orm.Funcionario;
import br.com.jpa.spring.data.repository.FuncionarioRepository;
import br.com.jpa.spring.data.specification.FuncionarioSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite um nome para consulta: ");
        String nome = scanner.next();

        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite um cpf para consulta: ");
        String cpf = scanner.next();

        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite um salário para consulta: ");
        Double salario = scanner.nextDouble();

        if(salario == 0) {
            salario = null;
        }

        System.out.println("Digite uma data de contratação para consulta: ");
        String data = scanner.next();
        LocalDate dataContratacao;

        if(data.equalsIgnoreCase("NULL")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse((data), formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(Specification.where(
                        FuncionarioSpecification.nome(nome)
                                .or(FuncionarioSpecification.cpf(cpf))
                                .or(FuncionarioSpecification.salario(salario))
                                .or(FuncionarioSpecification.dataContratacao(dataContratacao))
                ));
        funcionarios.forEach(System.out::println);
    }
}
