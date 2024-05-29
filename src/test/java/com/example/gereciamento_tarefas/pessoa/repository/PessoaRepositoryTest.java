package com.example.gereciamento_tarefas.pessoa.repository;

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
@Sql(scripts = {"classpath:/pessoa.sql"})
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Test
    public void findPessoasComTotalHorasGastas_deveRetornarPessoasComHorasGastas() {
        assertThat(repository.findPessoasComTotalHorasGastas())
                .extracting("nome", "departamento", "duracao")
                .containsExactly(Tuple.tuple("Maria Aparecida", "Finaceiro", 45L),
                        Tuple.tuple("Gustavo Lima", "Administrativo", 30L));
    }

    @Test
    public void findMediaHorasGastasPorTarefa_deveRetornarPessoasComHorasGastasPorTarefa() {
        var dataInicio = LocalDate.of(2024, 01, 01);
        var dataFinal = LocalDate.of(2024, 12, 31);

        assertThat(repository.findMediaHorasGastasPorTarefa("Maria Aparecida", dataInicio, dataFinal))
                .isEqualTo(22.5);
    }
}
