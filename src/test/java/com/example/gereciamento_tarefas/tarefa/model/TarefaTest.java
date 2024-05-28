package com.example.gereciamento_tarefas.tarefa.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.departamento.enums.EDepartamento.COMERCIAL;
import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.umaTarefaRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class TarefaTest {

    @Test
    public void convertFrom_deveRetornarTarefa_quandoSolicitado() {
        assertThat(Tarefa.convertFrom(umaTarefaRequest()))
                .extracting("titulo", "descricao", "prazo", "departamento", "duracao", "finalizado")
                .containsExactly("Ligar para os clientes",
                        "afim de aumentar as vendas, é solicitado entrar em contato com os clientes",
                        LocalDate.of(2024, 05, 29), COMERCIAL, 2, false);
    }
}
