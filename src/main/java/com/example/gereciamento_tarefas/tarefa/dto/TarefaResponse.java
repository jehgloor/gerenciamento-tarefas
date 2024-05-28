package com.example.gereciamento_tarefas.tarefa.dto;

import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TarefaResponse {

    private String titulo;
    private String descricao;
    private Date prazo;
    private String departamento;
    private Integer duracao;
    private Integer pessoaId;

    public static TarefaResponse convertFrom(Tarefa tarefa) {
        return TarefaResponse.builder()
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .prazo(tarefa.getPrazo())
                .departamento(tarefa.getDepartamento().getDescricao())
                .duracao(tarefa.getDuracao())
                .pessoaId(tarefa.getPessoa() != null ? tarefa.getPessoa().getId() : null)
                .build();
    }
}