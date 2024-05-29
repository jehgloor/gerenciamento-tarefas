package com.example.gereciamento_tarefas.tarefa.controller;

import com.example.gereciamento_tarefas.tarefa.dto.TarefaAlocarPessoaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponse save(@Validated @RequestBody TarefaRequest request) {
        return tarefaService.save(request);
    }

    @PutMapping("/alocar/{id}")
    public TarefaResponse alocarPessoaNaTarefa(@PathVariable Integer id, @RequestBody TarefaAlocarPessoaRequest request) {
        return tarefaService.alocarPessoaNaTarefa(id, request);
    }

    @PutMapping("/finalizar/{id}")
    public TarefaResponse finalizarTarefa(@PathVariable Integer id) {
        return tarefaService.finalizarTarefa(id);
    }
}
