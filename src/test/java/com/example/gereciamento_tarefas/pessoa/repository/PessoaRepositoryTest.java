package com.example.gereciamento_tarefas.pessoa.repository;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

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
                .containsExactly(Tuple.tuple("Maria Aparecida", "COMERCIAL", 60L),
                        Tuple.tuple("Gustavo Lima", "FINANCEIRO", 30L));

    }
}
