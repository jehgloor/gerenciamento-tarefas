package com.example.gereciamento_tarefas.pessoa.helper;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;

public class PessoaHelper {

    public static PessoaRequest umaPessoaRequest() {
        return PessoaRequest.builder()
                .departamento(EDepartamento.COMERCIAL)
                .nome("Maria Aparecida")
                .build();
    }

    public static Pessoa umaPessoa(Integer id) {
        return Pessoa.builder()
                .id(id)
                .departamento(EDepartamento.COMERCIAL)
                .nome("Maria Aparecida")
                .build();
    }
}
