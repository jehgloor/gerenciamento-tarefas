package com.example.gereciamento_tarefas.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPessoaPorNomeEPeriodoRequest {

    @NotBlank
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
