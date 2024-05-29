package com.example.gereciamento_tarefas.tarefa.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;
import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.umaTarefaRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class TarefaTest {

    @Test
    public void convertFrom_deveRetornarTarefa_quandoSolicitado() {
        assertThat(Tarefa.convertFrom(umaTarefaRequest(), umDepartamento(1, "Financeiro")))
                .extracting("titulo", "descricao", "prazo", "departamento.titulo", "duracao", "finalizado")
                .containsExactly("Ligar para os clientes",
                        "afim de aumentar as vendas, é solicitado entrar em contato com os clientes",
                        LocalDate.of(2024, 05, 29), "Financeiro", 2, false);
    }
}
