package com.example.gereciamento_tarefas.departamento.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.departamento.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;
import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamentoResponseInterface;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentoServiceTest {

    @InjectMocks
    private DepartamentoService departamentoService;
    @Mock
    private DepartamentoRepository departamentoRepository;

    @Test
    public void getDepartamentos_deveRetornarAQuantidadeDePessoasEDeTarefasParaCadaDepartamento() {
        when(departamentoRepository.findDepartamentosComQtdPessoasETarefas())
                .thenReturn(List.of(
                        umDepartamentoResponseInterface("Financeiro"),
                        umDepartamentoResponseInterface("Comercial")));

        assertThat(departamentoService.getDepartamentos())
                .extracting("titulo", "quantidadePessoas", "quantidadeTarefas")
                .containsExactly(tuple("Financeiro", 4, 3),
                        tuple("Comercial", 4, 3));
    }

    @Test
    public void findById_deveLancarException_quandoIdNaoEncontrado() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departamentoService.findById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("O Departamento não foi encontrado.");

        verify(departamentoRepository).findById(1);
    }

    @Test
    public void findById_deveRetornarDepartamento_quandoIdEncontrado() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(umDepartamento(1, "Financeiro")));

        assertThat(departamentoService.findById(1))
                .extracting("id", "titulo")
                .containsExactly(1, "Financeiro");

        verify(departamentoRepository).findById(1);
    }

}
