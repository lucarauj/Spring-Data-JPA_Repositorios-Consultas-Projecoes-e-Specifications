package br.com.jpa.spring.data.service;

import br.com.jpa.spring.data.orm.Cargo;
import br.com.jpa.spring.data.orm.Funcionario;
import br.com.jpa.spring.data.orm.UnidadeTrabalho;
import br.com.jpa.spring.data.repository.CargoRepository;
import br.com.jpa.spring.data.repository.FuncionarioRepository;
import br.com.jpa.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncionarioService {


        private Boolean system = true;
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        private final CargoRepository cargoRepository;
        private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
        private final FuncionarioRepository repository;
    public FuncionarioService(CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, FuncionarioRepository repository) {
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.repository = repository;
    }


    public void inicial(Scanner scanner) {
            while (system) {
                System.out.println("\nESCOLHA UMA OPÇÃO:");
                System.out.println("0 - Sair");
                System.out.println("1 - Salvar");
                System.out.println("2 - Atualizar");
                System.out.println("3 - Visualizar");
                System.out.println("4 - Deletar");

                int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        salvar(scanner);
                        break;
                    case 2:
                        atualizar(scanner);
                        break;
                    case 3:
                        visualizar(scanner);
                        break;
                    case 4:
                        deletar(scanner);
                        break;
                    default:
                        system = false;
                }
            }
        }

        private void salvar(Scanner scanner) {
            System.out.println("Nome do funcionário:");
            scanner.nextLine();
            String nome = scanner.nextLine();

            System.out.println("CPF:");
            String cpf = scanner.nextLine();

            System.out.println("Salário:");
            Double salario = scanner.nextDouble();

            System.out.println("Data de contratação (dia/mês/ano):");
            String dataContratacao = scanner.next();

            System.out.println("Cargo ID:");
            Integer cargoId = scanner.nextInt();

            List<UnidadeTrabalho> unidades = unidade(scanner);

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setSalario(salario);
            funcionario.setDataContratacao(LocalDate.parse((dataContratacao), formatter));
            Optional<Cargo> cargo = cargoRepository.findById(cargoId);
            funcionario.setCargo(cargo.get());
            funcionario.setUnidades(unidades);

            repository.save(funcionario);
            System.out.println("Registro salvo!");
        }

        private List<UnidadeTrabalho> unidade(Scanner scanner) {
            Boolean isTrue = true;
            List<UnidadeTrabalho> unidades = new ArrayList<>();

            while (isTrue) {
                System.out.println("Digite a unidadeId (Para sair digite 0)");
                Integer unidadeId = scanner.nextInt();

                if (unidadeId != 0) {
                    Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                    unidades.add(unidade.get());
                } else {
                    isTrue = false;
                }
            }
            return unidades;
        }

        private void atualizar(Scanner scanner) {
            System.out.println("Digite o ID do registro:");
            int id = scanner.nextInt();
            Optional<Funcionario> buscarFuncionario = repository.findById(id);

            if(buscarFuncionario.isPresent()){

                System.out.println("Nome do funcionário:");
                scanner.nextLine();
                String nome = scanner.nextLine();

                System.out.println("CPF:");
                scanner.nextLine();
                String cpf = scanner.nextLine();

                System.out.println("Salário:");
                Double salario = scanner.nextDouble();

                System.out.println("Data de contratação (dia/mês/ano):");
                String dataContratacao = scanner.next();

                System.out.println("Cargo ID:");
                Integer cargoId = scanner.nextInt();

                List<UnidadeTrabalho> unidades = unidade(scanner);

                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setSalario(salario);
                funcionario.setDataContratacao(LocalDate.parse((dataContratacao), formatter));
                Optional<Cargo> cargo = cargoRepository.findById(cargoId);
                funcionario.setCargo(cargo.get());

                repository.save(funcionario);
                System.out.println("Registro atualizado!");
            } else {
                System.out.println("Registro não encontrado");
            }
        }

        private void visualizar(Scanner scanner) {
            System.out.println("Qual página você deseja visualizar?");
            Integer page = scanner.nextInt();

            Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());

            Page<Funcionario> funcionarios = repository.findAll(pageable);

            System.out.println(funcionarios);
            System.out.println("Página atual: " + funcionarios.getNumber());
            System.out.println("Total de elementos: " + funcionarios.getTotalElements());
            funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
        }

        private void deletar(Scanner scanner) {
            System.out.println("Digite o ID do registro:");
            int id = scanner.nextInt();
            Optional<Funcionario> buscarFuncionario = repository.findById(id);

            if(buscarFuncionario.isPresent()){
                repository.deleteById(id);
                System.out.println("Registro deletado!");
            } else {
                System.out.println("Registro não encontrado");
            }
        }
    }
