package com.example.gereciamento_tarefas.departamento.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
@Sql(scripts = {"classpath:/pessoa.sql"})
public class DepartamentoRepositoryTest {

    @Autowired
    DepartamentoRepository repository;

    @Test
    public void findDepartamentosComQtdPessoasETarefas() {
        assertThat(repository.findDepartamentosComQtdPessoasETarefas())
                .extracting("titulo", "quantidadePessoas", "quantidadeTarefas")
                .containsExactly(tuple("Finaceiro", 2, 1),
                        tuple("Administrativo", 1, 2),
                        tuple("Comercial", 0, 0),
                        tuple("Juridico", 0, 0));
    }
}
