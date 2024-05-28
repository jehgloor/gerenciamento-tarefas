package com.example.gereciamento_tarefas.pessoa.dto;

import org.junit.jupiter.api.Test;

import static com.example.gereciamento_tarefas.departamento.enums.EDepartamento.COMERCIAL;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaResponseTest {

    @Test
    public void convertFrom_deveRetornarPessoa_quandoSolicitado() {
        assertThat(PessoaResponse.convertFrom(umaPessoa(1)))
                .extracting("nome", "departamento")
                .containsExactly("Maria Aparecida", "Comercial");
    }

    @Test
    public void convertFrom_deveRetornarPessoaSemDepartamento_quandoDepartamentoNull() {
        var pessoa = umaPessoa(1);
        pessoa.setDepartamento(null);
        assertThat(PessoaResponse.convertFrom(pessoa))
                .extracting("nome", "departamento")
                .containsExactly("Maria Aparecida", null);
    }
}
