package com.example.gereciamento_tarefas.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPessoaPorNomeEPeriodoRequest {

    @NotBlank
    private String nome;
    private Date dataInicio;
    private Date dataFim;
}
