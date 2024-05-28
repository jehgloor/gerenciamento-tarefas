package com.example.gereciamento_tarefas.tarefa.dto;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TarefaRequest {

    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private EDepartamento departamento;
    private Integer duracao;
}
