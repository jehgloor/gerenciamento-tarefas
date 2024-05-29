package com.example.gereciamento_tarefas.tarefa.repository;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    int countByDepartamento(EDepartamento departamento);


    @Query(value = "SELECT * FROM Tarefa t " +
            "WHERE t.pessoa_id IS NULL " +
            "ORDER BY t.prazo ASC " +
            "LIMIT :qtd", nativeQuery = true)
    List<Tarefa> pendentes(Integer qtd);
}
