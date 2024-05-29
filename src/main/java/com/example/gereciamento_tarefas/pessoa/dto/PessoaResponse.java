package com.example.gereciamento_tarefas.pessoa.dto;

import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaResponse {

    private String nome;
    private String tituloDepartamento;

    public static PessoaResponse convertFrom(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .tituloDepartamento(pessoa.getDepartamento().getTitulo())
                .build();
    }
}
