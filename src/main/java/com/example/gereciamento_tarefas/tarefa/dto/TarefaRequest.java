package com.example.gereciamento_tarefas.tarefa.dto;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TarefaRequest {

    @NotBlank
    private String titulo;
    private String descricao;
    @NotNull
    private LocalDate prazo;
    @NotNull
    private EDepartamento departamento;
    @NotNull
    private Integer duracao;
}
