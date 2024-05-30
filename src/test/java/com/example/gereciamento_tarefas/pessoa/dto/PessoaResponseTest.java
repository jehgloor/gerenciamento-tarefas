package com.example.gereciamento_tarefas.pessoa.dto;

import org.junit.jupiter.api.Test;

import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaResponseTest {

    @Test
    public void convertFrom_deveRetornarPessoaResponse_quandoSolicitado() {
        assertThat(PessoaResponse.convertFrom(umaPessoa(1)))
                .extracting("nome", "tituloDepartamento")
                .containsExactly("Maria Aparecida", "Financeiro");
    }
}
