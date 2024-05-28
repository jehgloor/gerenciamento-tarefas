package com.example.gereciamento_tarefas.pessoa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaDepartamentoInterface;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.gereciamento_tarefas.departamento.enums.EDepartamento.COMERCIAL;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.*;
import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.umaTarefaParaCalcularMediaDuracao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;
    @Mock
    private PessoaRepository repository;

    @Test
    public void save_deveSalvarPessoa_quandoRequestPassadoCertos() {
        when(repository.save(any(Pessoa.class))).thenReturn(umaPessoa(1));

        assertThat(service.save(umaPessoaRequest()))
                .extracting("nome", "departamento")
                .containsExactly("Maria Aparecida", "Comercial");

        verify(repository).save(any(Pessoa.class));
    }

    @Test
    public void edit_deveEditarPessoa_quandoIdEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.of(umaPessoa(1)));
        when(repository.save(any(Pessoa.class))).thenReturn(umaPessoa(1));

        assertThat(service.edit(1, umaPessoaRequest()))
                .extracting("nome", "departamento")
                .containsExactly("Maria Aparecida", "Comercial");

        verify(repository).save(any(Pessoa.class));
        verify(repository).findById(1);
    }

    @Test
    public void edit_deveLancarException_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.edit(1, umaPessoaRequest()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrado.");

        verify(repository).findById(1);

        verify(repository, never()).save(any(Pessoa.class));
        verify(repository).findById(1);
    }

    @Test
    public void delete_deveDeletar_quandoIdEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.of(umaPessoa(1)));

        service.delete(1);

        verify(repository).findById(1);
        verify(repository).delete(umaPessoa(1));
    }

    @Test
    public void delete_naoDeveDeletarPessoa_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrado.");

        verify(repository).findById(1);
        verify(repository, never()).delete(umaPessoa(1));
    }

    @Test
    public void findById_deveLancarException_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrado.");

        verify(repository).findById(1);
    }

    @Test
    public void findById_deveRetornarPessoa_quandoIdEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.of(umaPessoa(1)));

        assertThat(service.findById(1))
                .extracting("id", "departamento", "nome")
                .containsExactly(1, COMERCIAL, "Maria Aparecida");

        verify(repository).findById(1);
    }

    @Test
    public void getAll_deveRetornarListaPessoaDepartamentoInterface_quandoSolicitado() {
        var pessoa1 = mock(PessoaDepartamentoInterface.class);
        var pessoa2 = mock(PessoaDepartamentoInterface.class);

        when(pessoa1.getNome()).thenReturn("Amanda Joaquina da Silva");
        when(pessoa1.getDepartamento()).thenReturn("ADMINISTRATIVO");
        when(pessoa1.getDuracao()).thenReturn(2L);

        when(pessoa2.getNome()).thenReturn("José Henrique");
        when(pessoa2.getDepartamento()).thenReturn("ADMINISTRATIVO");
        when(pessoa2.getDuracao()).thenReturn(4L);

        var mockResponse = List.of(pessoa1, pessoa2);
        when(repository.findPessoasComTotalHorasGastas()).thenReturn(mockResponse);


        assertThat(service.getAll()).extracting("nome", "departamento", "duracao")
                .containsExactly(tuple("Amanda Joaquina da Silva", "ADMINISTRATIVO", 2L),
                        tuple("José Henrique", "ADMINISTRATIVO", 4L));

    }

    @Test
    public void mediaGasto_deveRetornarMediaGasto_quandoExistirDados() {
        var nome = "Maria Aparecida";
        var dataInicio = LocalDate.of(2024, 1, 1);
        var dataFim = LocalDate.of(2024, 12, 31);

        var tarefas = List.of(
                umaTarefaParaCalcularMediaDuracao(5),
                umaTarefaParaCalcularMediaDuracao(4),
                umaTarefaParaCalcularMediaDuracao(3)
        );

        when(repository.findMediaHorasGastasPorTarefa(nome, dataInicio, dataFim))
                .thenReturn(tarefas.stream().mapToInt(Tarefa::getDuracao).average().orElse(0.0));

        var media = service.mediaGasto(umaBuscaPessoaPorNomeEPeriodoRequest());

        assertThat(media).isEqualTo(4.0);

        verify(repository).findMediaHorasGastasPorTarefa(nome, dataInicio, dataFim);
    }

}
