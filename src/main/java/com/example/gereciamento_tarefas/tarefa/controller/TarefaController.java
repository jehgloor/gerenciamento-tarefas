package com.example.gereciamento_tarefas.tarefa.controller;

import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    public TarefaResponse save(@RequestBody TarefaRequest request) {
        return tarefaService.save(request);
    }

    @PutMapping("/finalizar/{id}")
    public TarefaResponse finalizarTarefa(@PathVariable Integer id) {
        return tarefaService.finalizarTarefa(id);
    }
}
