package com.example.gereciamento_tarefas.tarefa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.comum.exception.ValidacaoException;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaResponse save(TarefaRequest request) {
        var tarefa = tarefaRepository.save(Tarefa.convertFrom(request));
        return TarefaResponse.convertFrom(tarefa);

    }

    private Tarefa findById(Integer id) {
        return tarefaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("A Tarefa não foi encontrada."));
    }

    public TarefaResponse finalizarTarefa(Integer id) {
        var tarefa = findById(id);

        if (tarefa.getFinalizado() == Boolean.TRUE) {
            throw new ValidacaoException("A tarefa já se encontra finalizado");
        }

        tarefa.setFinalizado(Boolean.TRUE);
        return TarefaResponse.convertFrom(tarefaRepository.save(tarefa));
    }
}
