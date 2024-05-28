package com.example.gereciamento_tarefas.tarefa.dto;

import com.example.gereciamento_tarefas.pessoa.enums.EDepartamento;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TarefaRequest {

    private String titulo;
    private String descricao;
    private Date prazo;
    private EDepartamento departamento;
    private Integer duracao;
}
