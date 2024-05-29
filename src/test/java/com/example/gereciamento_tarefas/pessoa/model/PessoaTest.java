package com.example.gereciamento_tarefas.pessoa.model;

import org.junit.jupiter.api.Test;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTest {

    @Test
    public void convertFrom_deveRetornarPessoa_quandoSolicitado() {
        assertThat(Pessoa.convertFrom(umaPessoaRequest(), umDepartamento(1, "Financeiro")))
                .extracting("nome", "departamento.titulo")
                .containsExactly("Maria Aparecida", "Financeiro");
    }
}
