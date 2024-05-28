package com.example.gereciamento_tarefas.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPessoaPorNomeEPeriodoRequest {

    private String nome;
    private Date dataInicio;
    private Date dataFim;
}
