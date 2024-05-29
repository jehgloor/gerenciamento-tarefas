package com.example.gereciamento_tarefas.departamento.service;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentoServiceTest {

    @InjectMocks
    private DepartamentoService departamentoService;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    public void getDepartamentos_deveRetornarAQuantidadeDePessoasEDeTarefasParaCadaDepartamento() {
        when(pessoaRepository.countByDepartamento(EDepartamento.RECURSOS_HUMANOS)).thenReturn(5);
        when(tarefaRepository.countByDepartamento(EDepartamento.RECURSOS_HUMANOS)).thenReturn(10);

        when(pessoaRepository.countByDepartamento(EDepartamento.FINANCEIRO)).thenReturn(3);
        when(tarefaRepository.countByDepartamento(EDepartamento.FINANCEIRO)).thenReturn(7);

        var resultado = departamentoService.getDepartamentos();

        assertNotNull(resultado);
        assertEquals(7, resultado.size());

        var departamentoRH = resultado.stream()
                .filter(dep -> dep.getNome().equals(EDepartamento.RECURSOS_HUMANOS.getDescricao()))
                .findFirst()
                .orElse(null);

        assertNotNull(departamentoRH);
        assertEquals(5, departamentoRH.getQuantidadePessoas());
        assertEquals(10, departamentoRH.getQuantidadeTarefas());


        var departamentoFinanceiro = resultado.stream()
                .filter(dep -> dep.getNome().equals(EDepartamento.FINANCEIRO.getDescricao()))
                .findFirst()
                .orElse(null);

        assertNotNull(departamentoFinanceiro);
        assertEquals(3, departamentoFinanceiro.getQuantidadePessoas());
        assertEquals(7, departamentoFinanceiro.getQuantidadeTarefas());
    }

}
