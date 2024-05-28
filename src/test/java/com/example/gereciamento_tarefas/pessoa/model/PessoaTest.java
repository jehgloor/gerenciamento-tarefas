package com.example.gereciamento_tarefas.pessoa.model;

import org.junit.jupiter.api.Test;

import static com.example.gereciamento_tarefas.departamento.enums.EDepartamento.COMERCIAL;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTest {

    @Test
    public void convertFrom_deveRetornarPessoa_quandoSolicitado() {
        assertThat(Pessoa.convertFrom(umaPessoaRequest()))
                .extracting("nome", "departamento")
                .containsExactly("Maria Aparecida", COMERCIAL);
    }
}
