package com.example.gereciamento_tarefas.tarefa.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.umaTarefa;
import static org.assertj.core.api.Assertions.assertThat;

public class TarefaResponseTest {

    @Test
    public void convertFrom_deveRetornarTarefa_quandoSolicitado() {
        assertThat(TarefaResponse.convertFrom(umaTarefa(1)))
                .extracting("titulo", "descricao", "prazo", "departamento", "duracao", "finalizado")
                .containsExactly("Ligar para os clientes",
                        "afim de aumentar as vendas, é solicitado entrar em contato com os clientes",
                        LocalDate.of(2024, 05, 29), "Comercial", 2, false);
    }

}
