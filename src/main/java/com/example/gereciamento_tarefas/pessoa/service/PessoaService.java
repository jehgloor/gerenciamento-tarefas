package com.example.gereciamento_tarefas.pessoa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
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

    public PessoaResponse edit(Integer id, PessoaRequest request) {
        var pessoa = findById(id);
        pessoa.setNome(request.getNome());
        pessoa.setDepartamento(request.getDepartamento());

        pessoaRepository.save(pessoa);

        return PessoaResponse.convertFrom(pessoa);
    }

    private Pessoa findById(Integer id) {
        return pessoaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("A Pessoa não foi encontrado."));
    }
}
