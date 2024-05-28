package com.example.gereciamento_tarefas.pessoa.controller;

import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaResponse;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public PessoaResponse save(@RequestBody PessoaRequest request) {
        return pessoaService.save(request);
    }
}
