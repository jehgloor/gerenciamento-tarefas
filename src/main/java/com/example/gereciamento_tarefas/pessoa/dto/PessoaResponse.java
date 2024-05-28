package com.example.gereciamento_tarefas.pessoa.dto;

import com.example.gereciamento_tarefas.pessoa.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaResponse {

    private String nome;
    private String departamento;

    public static PessoaResponse convertFrom(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .departamento(pessoa.getDepartamento().getDescricao())
                .build();
    }
}
