package com.example.gereciamento_tarefas.departamento.repository;

import com.example.gereciamento_tarefas.departamento.dto.DepartamentoResponseInterface;
import com.example.gereciamento_tarefas.departamento.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Query(value = "SELECT d.titulo as titulo, " +
            "       (SELECT COUNT(*) FROM pessoa p WHERE p.id_departamento = d.id) as quantidadePessoas, " +
            "       (SELECT COUNT(*) FROM tarefa t WHERE t.id_departamento = d.id) as quantidadeTarefas " +
            "FROM departamento d" , nativeQuery = true)
    List<DepartamentoResponseInterface> findDepartamentosComQtdPessoasETarefas();
}
