package com.example.gereciamento_tarefas.tarefa.dto;

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
    private Integer duracao;
    @NotNull
    private Integer idDepartamento;
}
