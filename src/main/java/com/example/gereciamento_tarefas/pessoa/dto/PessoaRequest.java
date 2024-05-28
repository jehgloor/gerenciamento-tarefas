package com.example.gereciamento_tarefas.pessoa.dto;

import com.example.gereciamento_tarefas.pessoa.enums.EDepartamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaRequest {

    private String nome;
    private EDepartamento departamento;
}
