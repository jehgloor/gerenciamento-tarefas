package com.example.gereciamento_tarefas.tarefa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.comum.exception.ValidacaoException;
import com.example.gereciamento_tarefas.departamento.service.DepartamentoService;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import com.example.gereciamento_tarefas.pessoa.service.PessoaService;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoa;
import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;
    @InjectMocks
    private TarefaService tarefaService;
    @Mock
    private TarefaRepository tarefaRepository;
    @Mock
    private PessoaService pessoaService;
    @Mock
    private DepartamentoService departamentoService;

    @Test
    public void save_deveSalvarTarefa_quandoRequestPassadoCertos() {
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(umaTarefa(1));
        when(departamentoService.findById(1)).thenReturn(umDepartamento(1, "Financeiro"));

        assertThat(tarefaService.save(umaTarefaRequest()))
                .extracting("titulo", "descricao", "prazo", "tituloDepartamento", "duracao", "finalizado")
                .containsExactly("LIGAR PARA OS CLIENTES", "Entre em contato com nossos clientes",
                        LocalDate.of(2024, 05, 29), "Financeiro", 2, false);

        verify(tarefaRepository).save(any(Tarefa.class));
    }

    @Test
    public void finalizarTarefa_deveLancarException_quandoTarefaJaSeEncontrarEmFinalizado() {
        var tarefa = umaTarefa(1);
        tarefa.setFinalizado(Boolean.TRUE);
        when(tarefaRepository.findById(1)).thenReturn(Optional.ofNullable(tarefa));

        assertThatThrownBy(() -> tarefaService.finalizarTarefa(1))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("A tarefa já se encontra finalizada.");

        verify(tarefaRepository).findById(1);
    }

    @Test
    public void finalizarTarefa_deveLancarException_quandoIdNaoEncontrado() {
        when(tarefaRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tarefaService.finalizarTarefa(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Tarefa não foi encontrada.");

        verify(tarefaRepository).findById(1);
    }

    @Test
    public void finalizarTarefa_deveFinalizarTarefa_quandoTarefaSeEncontrarNaoFinalizado() {
        var tarefa = umaTarefa(1);
        tarefa.setFinalizado(Boolean.FALSE);
        when(tarefaRepository.findById(1)).thenReturn(Optional.ofNullable(tarefa));
        assertThat(tarefa.getFinalizado()).isEqualTo(false);

        tarefaService.finalizarTarefa(1);

        assertThat(tarefa.getFinalizado()).isEqualTo(true);

        verify(tarefaRepository).findById(1);
    }

    @Test
    public void alocarPessoaNaTarefa_deveAlocarPessoaNaTarefa_seIdPessoaEIdTarefaEncontrado() {
        var dpto = umDepartamento(1, "Financeiro");
        var tarefa = umaTarefa(1);
        tarefa.setDepartamento(dpto);
        var pessoa = umaPessoa(1);
        pessoa.setDepartamento(dpto);
        when(pessoaService.findById(1)).thenReturn(pessoa);
        when(tarefaRepository.findById(1)).thenReturn(Optional.ofNullable(tarefa));

        assertThat(tarefa.getPessoa()).isEqualTo(null);

        tarefaService.alocarPessoaNaTarefa(1, umaTarefaAlocarPessoaRequest());

        assertThat(tarefa.getPessoa()).isEqualTo(pessoa);
    }

    @Test
    public void alocarPessoaNaTarefa_deveRetornarException_seIdTarefaNaoEncontrado() {
        var pessoa = umaPessoa(1);
        when(pessoaService.findById(1)).thenReturn(pessoa);
        when(tarefaRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> tarefaService.alocarPessoaNaTarefa(1, umaTarefaAlocarPessoaRequest()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Tarefa não foi encontrada.");
    }

    @Test
    public void alocarPessoaNaTarefa_deveRetornarException_seTarefaEPessoaNaoPossuirOMesmoDepartamento() {
        var pessoa = umaPessoa(1);
        pessoa.setDepartamento(umDepartamento(1, "Financeiro"));
        var tarefa = umaTarefa(1);
        tarefa.setDepartamento(umDepartamento(2, "Comercial"));
        when(pessoaService.findById(1)).thenReturn(pessoa);
        when(tarefaRepository.findById(1)).thenReturn(Optional.ofNullable(tarefa));
        assertThatThrownBy(() -> tarefaService.alocarPessoaNaTarefa(1, umaTarefaAlocarPessoaRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("A tarefa e a pessoa devem ser do mesmo departamento.");
    }

    @Test
    public void pendentes_deveRetornarListaTarefaResponse_quandoSolicitado() {
        when(tarefaRepository.pendentes(3)).thenReturn(List.of(umaTarefa(4), umaTarefa(5), umaTarefa(6)));
        assertThat(tarefaService.pendentes())
                .extracting("titulo", "descricao", "prazo", "tituloDepartamento", "duracao", "finalizado")
                .containsExactly(tuple("LIGAR PARA OS CLIENTES", "Entre em contato com nossos clientes",
                        LocalDate.of(2024, 05, 29), "Financeiro", 2, false),
                        tuple("LIGAR PARA OS CLIENTES", "Entre em contato com nossos clientes",
                                LocalDate.of(2024, 05, 29), "Financeiro", 2, false),
                        tuple("LIGAR PARA OS CLIENTES", "Entre em contato com nossos clientes",
                                LocalDate.of(2024, 05, 29), "Financeiro", 2, false));
    }
}
