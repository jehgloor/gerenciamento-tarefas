package com.example.gereciamento_tarefas.pessoa.repository;

import com.example.gereciamento_tarefas.pessoa.dto.PessoaDepartamentoInterface;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query(value = "SELECT p.nome as nome, (SELECT d.titulo FROM DEPARTAMENTO d WHERE d.id = p.id_departamento) as departamento, SUM(t.duracao) as duracao " +
            "FROM Tarefa t JOIN Pessoa p on p.id = t.pessoa_id " +
            "GROUP BY p.id, p.id_departamento", nativeQuery = true)
    List<PessoaDepartamentoInterface> findPessoasComTotalHorasGastas();

    @Query("SELECT AVG(t.duracao) " +
            "FROM Pessoa p JOIN p.tarefas t " +
            "WHERE p.nome = :nome " +
            "AND t.prazo BETWEEN :dataInicio AND :dataFim")
    Double findMediaHorasGastasPorTarefa(String nome, LocalDate dataInicio, LocalDate dataFim);
}
