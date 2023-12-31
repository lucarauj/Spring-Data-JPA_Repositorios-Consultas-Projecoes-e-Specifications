package br.com.jpa.spring.data.service;

import br.com.jpa.spring.data.orm.Cargo;
import br.com.jpa.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CargoService {

    private Boolean system = true;

    private final CargoRepository repository;

    public CargoService(CargoRepository repository) {
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
                    visualizar();
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
        System.out.println("Descrição do cargo:");
        scanner.nextLine();
        String descricao = scanner.nextLine();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Registro salvo!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o ID do registro:");
        int id = scanner.nextInt();
        Optional<Cargo> buscarCargo = repository.findById(id);

        if(buscarCargo.isPresent()){
            System.out.println("Nova descrição do cargo:");
            scanner.nextLine();
            String descricao = scanner.nextLine();
            Cargo cargo = new Cargo();
            cargo.setId(id);
            cargo.setDescricao(descricao);
            repository.save(cargo);
            System.out.println("Registro atualizado!");
        } else {
            System.out.println("Registro não encontrado");
        }
    }

    private void visualizar() {
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo.toString()));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o ID do registro:");
        int id = scanner.nextInt();
        Optional<Cargo> buscarCargo = repository.findById(id);

        if(buscarCargo.isPresent()){
            repository.deleteById(id);
            System.out.println("Registro deletado!");
        } else {
            System.out.println("Registro não encontrado");
        }
    }
}
