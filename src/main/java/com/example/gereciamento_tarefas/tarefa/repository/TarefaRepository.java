package com.example.gereciamento_tarefas.tarefa.repository;

import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
