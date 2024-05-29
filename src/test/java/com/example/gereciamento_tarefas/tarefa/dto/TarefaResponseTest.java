package com.example.gereciamento_tarefas.tarefa.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.umaTarefa;
import static org.assertj.core.api.Assertions.assertThat;

public class TarefaResponseTest {

    @Test
    public void convertFrom_deveRetornarTarefa_quandoSolicitado() {
        assertThat(TarefaResponse.convertFrom(umaTarefa(1)))
                .extracting("titulo", "descricao", "prazo", "tituloDepartamento", "duracao", "finalizado")
                .containsExactly("LIGAR PARA OS CLIENTES", "Entre em contato com nossos clientes",
                        LocalDate.of(2024, 05, 29), "Financeiro", 2, false);
    }

}
