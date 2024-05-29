package com.example.gereciamento_tarefas.tarefa.controller;

import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import com.example.gereciamento_tarefas.tarefa.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.gereciamento_tarefas.helper.TestsHelper.convertObjectToJsonBytes;
import static com.example.gereciamento_tarefas.tarefa.helper.TarefaHelper.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TarefaControllerTest {

    private static final String BASE_URL = "/tarefas";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private TarefaService tarefaService;
    @MockBean
    private TarefaRepository tarefaRepository;

    @Test
    public void save_deveRetornar201_seRequestPassadoCorretamente() throws Exception {
        var request = umaTarefaRequest();
        var response = umaTarefaResponse();

        when(tarefaService.save(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tituloDepartamento", is("Comercial")))
                .andExpect(jsonPath("$.prazo", is("2024-05-29")))
                .andExpect(jsonPath("$.duracao", is(2)))
                .andExpect(jsonPath("$.finalizado", is(false)))
                .andExpect(jsonPath("$.titulo", is("LIGAR PARA OS CLIENTES")))
                .andExpect(jsonPath("$.descricao", is("Entre em contato com nossos clientes")));

        verify(tarefaService).save(request);
    }

    @Test
    public void save_deveRetornar400_seCamposObrigatoriosVazio() throws Exception {
        var request = umaTarefaRequest();
        var response = umaTarefaResponse();
        request.setIdDepartamento(null);
        request.setTitulo(null);
        request.setPrazo(null);
        request.setDuracao(null);

        when(tarefaService.save(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                        "O campo titulo must not be blank",
                        "O campo prazo must not be null",
                        "O campo idDepartamento must not be null",
                        "O campo duracao must not be null")));

        verify(tarefaService, never()).save(request);
    }

    @Test
    public void alocarPessoaNaTarefa_deveRetornar200_seRequestPassadoCorretamente() throws Exception {
        var request = umaTarefaAlocarPessoaRequest();
        var response = umaTarefaResponse();

        when(tarefaService.alocarPessoaNaTarefa(1, request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/alocar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tituloDepartamento", is("Comercial")))
                .andExpect(jsonPath("$.prazo", is("2024-05-29")))
                .andExpect(jsonPath("$.duracao", is(2)))
                .andExpect(jsonPath("$.finalizado", is(false)))
                .andExpect(jsonPath("$.titulo", is("LIGAR PARA OS CLIENTES")))
                .andExpect(jsonPath("$.descricao", is("Entre em contato com nossos clientes")));

        verify(tarefaService).alocarPessoaNaTarefa(1, request);
    }

    @Test
    public void finalizarTarefa_deveRetornar200_seRequestPassadoCorretamente() throws Exception {
        var request = umaTarefaAlocarPessoaRequest();
        var response = umaTarefaResponse();

        when(tarefaService.finalizarTarefa(1)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/finalizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tituloDepartamento", is("Comercial")))
                .andExpect(jsonPath("$.prazo", is("2024-05-29")))
                .andExpect(jsonPath("$.duracao", is(2)))
                .andExpect(jsonPath("$.finalizado", is(false)))
                .andExpect(jsonPath("$.titulo", is("LIGAR PARA OS CLIENTES")))
                .andExpect(jsonPath("$.descricao", is("Entre em contato com nossos clientes")));

        verify(tarefaService).finalizarTarefa(1);
    }

    @Test
    public void pendentes_deveRetornar200_quandoSolicitado() throws Exception {
        when(tarefaService.pendentes()).thenReturn(List.of(umaTarefaResponse()));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/pendentes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tituloDepartamento", is("Comercial")))
                .andExpect(jsonPath("$[0].prazo", is("2024-05-29")))
                .andExpect(jsonPath("$[0].duracao", is(2)))
                .andExpect(jsonPath("$[0].finalizado", is(false)))
                .andExpect(jsonPath("$[0].titulo", is("LIGAR PARA OS CLIENTES")))
                .andExpect(jsonPath("$[0].descricao", is("Entre em contato com nossos clientes")));

        verify(tarefaService).pendentes();
    }
}
