package com.example.gereciamento_tarefas.tarefa.service;

import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public TarefaResponse save(TarefaRequest request) {
        var tarefa = repository.save(Tarefa.convertFrom(request));
        return TarefaResponse.convertFrom(tarefa);

    }
}
