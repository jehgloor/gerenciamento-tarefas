package com.example.gereciamento_tarefas.departamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoResponse {
    private String nome;
    private int quantidadePessoas;
    private int quantidadeTarefas;
}
