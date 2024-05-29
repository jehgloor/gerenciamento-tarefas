package com.example.gereciamento_tarefas.pessoa.repository;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@Sql(scripts = {"classpath:/pessoa.sql", "classpath:/tarefa.sql"})
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Test
    public void findPessoasComTotalHorasGastas_deveRetornarPessoasComHorasGastas() {
        assertThat(repository.findPessoasComTotalHorasGastas())
                .extracting("nome", "departamento", "duracao")
                .containsExactly(Tuple.tuple("Maria Aparecida", "COMERCIAL", 45L),
                        Tuple.tuple("Gustavo Lima", "FINANCEIRO", 30L));

    }

    @Test
    public void findMediaHorasGastasPorTarefa_deveRetornarPessoasComHorasGastasPorTarefa() {
        var dataInicio = LocalDate.of(2024, 01, 01);
        var dataFinal = LocalDate.of(2024, 12, 31);

        assertThat(repository.findMediaHorasGastasPorTarefa("Maria Aparecida", dataInicio, dataFinal))
                .isEqualTo(22.5);
    }

    @Test
    public void countByDepartamento_deveRetornarQuantidadeDePessoasPorDepartamento() {
        assertThat(repository.countByDepartamento(EDepartamento.COMERCIAL))
                .isEqualTo(2);
        assertThat(repository.countByDepartamento(EDepartamento.FINANCEIRO))
                .isEqualTo(1);
        assertThat(repository.countByDepartamento(EDepartamento.RECURSOS_HUMANOS))
                .isEqualTo(0);
        assertThat(repository.countByDepartamento(EDepartamento.ADMINISTRATIVO))
                .isEqualTo(0);
        assertThat(repository.countByDepartamento(EDepartamento.MARKETING))
                .isEqualTo(0);
        assertThat(repository.countByDepartamento(EDepartamento.JURIDICO))
                .isEqualTo(0);
        assertThat(repository.countByDepartamento(EDepartamento.FINANCEIRO))
                .isEqualTo(1);
        assertThat(repository.countByDepartamento(EDepartamento.OPERACIONAL))
                .isEqualTo(0);
    }
}
