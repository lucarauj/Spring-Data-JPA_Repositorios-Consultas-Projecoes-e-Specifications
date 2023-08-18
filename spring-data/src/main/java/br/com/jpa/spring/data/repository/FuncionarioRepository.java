package br.com.jpa.spring.data.repository;

import br.com.jpa.spring.data.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}
