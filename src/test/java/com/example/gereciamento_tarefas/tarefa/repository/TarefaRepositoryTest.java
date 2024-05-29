package com.example.gereciamento_tarefas.tarefa.repository;

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
public class TarefaRepositoryTest {

    @Autowired
    TarefaRepository repository;

    @Test
    public void pendentes_deveRetonarasTresTarefasMaisAntigasSemEstarAtribuidoPessoas() {
        assertThat(repository.pendentes(3))
                .extracting("id", "titulo", "descricao", "prazo", "departamento", "duracao", "finalizado")
                .containsExactly(
                        Tuple.tuple(5, "AÇÃO 27676-98", "Ação",
                                LocalDate.of(2024, 03, 30), null, 30, false),
                        Tuple.tuple(6, "POSTAR", "Postagem na rede social no Instagram",
                                LocalDate.of(2024, 03, 30), null, 30, false),
                        Tuple.tuple(4, "EMIÇÃO DOS HOLERITES", "Emitir os holerites",
                                LocalDate.of(2024, 04, 30), null, 30, false)
                );
    }
}
