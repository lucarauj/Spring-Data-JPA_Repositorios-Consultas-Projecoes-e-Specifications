package br.com.jpa.spring.data.repository;

import br.com.jpa.spring.data.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    //Derived Query
    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

    //JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

    //Native Query
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
    nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

}
