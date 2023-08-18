package br.com.jpa.spring.data.service;

import br.com.jpa.spring.data.orm.UnidadeTrabalho;
import br.com.jpa.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class UnidadeTrabalhoService {

    private Boolean system = true;

    private final UnidadeTrabalhoRepository repository;

    public UnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
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
        System.out.println("Nome da Unidade:");
        scanner.nextLine();
        String descricao = scanner.nextLine();

        System.out.println("Endereço:");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        repository.save(unidadeTrabalho);
        System.out.println("Registro salvo!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o ID do registro:");
        int id = scanner.nextInt();
        Optional<UnidadeTrabalho> buscarUnidade = repository.findById(id);

        if(buscarUnidade.isPresent()){

            System.out.println("Nova descrição da Unidade:");
            scanner.nextLine();
            String descricao = scanner.nextLine();

            System.out.println("Novo endereço:");
            scanner.nextLine();
            String endereco = scanner.nextLine();

            UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
            unidadeTrabalho.setId(id);
            unidadeTrabalho.setDescricao(descricao);
            unidadeTrabalho.setEndereco(endereco);

            repository.save(unidadeTrabalho);
            System.out.println("Registro atualizado!");
        } else {
            System.out.println("Registro não encontrado");
        }
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidades = repository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade.toString()));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o ID do registro:");
        int id = scanner.nextInt();
        Optional<UnidadeTrabalho> buscarUnidade = repository.findById(id);

        if(buscarUnidade.isPresent()){
            repository.deleteById(id);
            System.out.println("Registro deletado!");
        } else {
            System.out.println("Registro não encontrado");
        }
    }
}