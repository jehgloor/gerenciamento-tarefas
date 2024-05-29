package com.example.gereciamento_tarefas.pessoa.helper;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.dto.BuscaPessoaPorNomeEPeriodoRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaResponse;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;

public class PessoaHelper {

    public static PessoaRequest umaPessoaRequest() {
        return PessoaRequest.builder()
                .idDepartamento(1)
                .nome("Maria Aparecida")
                .build();
    }

    public static Pessoa umaPessoa(Integer id) {
        return Pessoa.builder()
                .id(id)
                .departamento(umDepartamento(1, "Financeiro"))
                .nome("Maria Aparecida")
                .build();
    }

    public static PessoaResponse umaPessoaResponse() {
        return PessoaResponse.builder()
                .tituloDepartamento(EDepartamento.COMERCIAL.getDescricao())
                .nome("Maria Aparecida")
                .build();
    }

    public static BuscaPessoaPorNomeEPeriodoRequest umaBuscaPessoaPorNomeEPeriodoRequest() {
        var dataInicio = LocalDate.of(2024, 01, 01);
        var dataFinal = LocalDate.of(2024, 12, 31);
        return BuscaPessoaPorNomeEPeriodoRequest.builder()
                .nome("Maria Aparecida")
                .dataInicio(dataInicio)
                .dataFim(dataFinal)
                .build();
    }
}
