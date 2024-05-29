package com.example.gereciamento_tarefas.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaRequest {

    @NotBlank
    private String nome;
    @NotNull
    private Integer idDepartamento;
}
