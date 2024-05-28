package com.example.gereciamento_tarefas.tarefa.dto;

import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TarefaResponse {

    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private String departamento;
    private Integer duracao;
    private Boolean finalizado;
    private Integer pessoaId;

    public static TarefaResponse convertFrom(Tarefa tarefa) {
        return TarefaResponse.builder()
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .prazo(tarefa.getPrazo())
                .departamento(tarefa.getDepartamento().getDescricao())
                .duracao(tarefa.getDuracao())
                .finalizado(tarefa.getFinalizado())
                .pessoaId(tarefa.getPessoa() != null ? tarefa.getPessoa().getId() : null)
                .build();
    }
}
