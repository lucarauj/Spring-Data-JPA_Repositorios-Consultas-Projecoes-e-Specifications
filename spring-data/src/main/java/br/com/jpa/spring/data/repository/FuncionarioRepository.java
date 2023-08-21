package br.com.jpa.spring.data.repository;

import br.com.jpa.spring.data.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);
}
