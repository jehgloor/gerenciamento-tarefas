package com.example.gereciamento_tarefas.departamento.service;

import com.example.gereciamento_tarefas.departamento.dto.DepartamentoResponse;
import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<DepartamentoResponse> getDepartamentos() {
        List<DepartamentoResponse> departamentos = new ArrayList<>();

        for (EDepartamento departamento : EDepartamento.values()) {
            int quantidadePessoas = pessoaRepository.countByDepartamento(departamento);
            int quantidadeTarefas = tarefaRepository.countByDepartamento(departamento);
            departamentos.add(new DepartamentoResponse(departamento.getDescricao(), quantidadePessoas, quantidadeTarefas));
        }

        return departamentos;
    }
}
