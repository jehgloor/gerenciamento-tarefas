package com.example.gereciamento_tarefas.departamento.controller;

import com.example.gereciamento_tarefas.departamento.repository.DepartamentoRepository;
import com.example.gereciamento_tarefas.departamento.service.DepartamentoService;
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

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamentoResponseInterface;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DepartamentoControllerTest {
    private static final String BASE_URL = "/departamentos";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private DepartamentoService departamentoService;
    @MockBean
    private DepartamentoRepository departamentoRepository;

    @Test
    public void getDepartamentos_deveRetornar200_quandoSolicitado() throws Exception {
        when(departamentoService.getDepartamentos())
                .thenReturn(List.of(umDepartamentoResponseInterface("Comercial")));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo", is("Comercial")))
                .andExpect(jsonPath("$[0].quantidadePessoas", is(4)))
                .andExpect(jsonPath("$[0].quantidadeTarefas", is(3)));

        verify(departamentoService).getDepartamentos();
    }
}
