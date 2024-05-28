package com.example.gereciamento_tarefas.pessoa.service;

import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaResponse;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public PessoaResponse save(PessoaRequest request) {
        var pessoa = pessoaRepository.save(Pessoa.convertFrom(request));
        return PessoaResponse.convertFrom(pessoa);
    }
}
