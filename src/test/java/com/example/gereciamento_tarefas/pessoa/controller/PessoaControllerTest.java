package com.example.gereciamento_tarefas.pessoa.controller;

import com.example.gereciamento_tarefas.pessoa.dto.BuscaPessoaPorNomeEPeriodoRequest;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import com.example.gereciamento_tarefas.pessoa.service.PessoaService;
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
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PessoaControllerTest {

    private static final String BASE_URL = "/pessoas";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private PessoaService pessoaService;
    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void save_deveRetornar201_seRequestPassadoCorretamente() throws Exception {
        var request = umaPessoaRequest();
        var response = umaPessoaResponse();

        when(pessoaService.save(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Maria Aparecida")))
                .andExpect(jsonPath("$.tituloDepartamento", is("Comercial")));

        verify(pessoaService).save(request);
    }

    @Test
    public void save_deveRetornar400_seCamposObrigatoriosVazio() throws Exception {
        var request = umaPessoaRequest();
        var response = umaPessoaResponse();
        request.setIdDepartamento(null);
        request.setNome(null);

        when(pessoaService.save(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                        "O campo nome must not be blank",
                        "O campo idDepartamento must not be null")));

        verify(pessoaService, never()).save(request);
    }

    @Test
    public void edit_deveRetornar200_seRequestPassadoCorretamente() throws Exception {
        var request = umaPessoaRequest();
        var response = umaPessoaResponse();

        when(pessoaService.edit(1, request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Maria Aparecida")))
                .andExpect(jsonPath("$.tituloDepartamento", is("Comercial")));

        verify(pessoaService).edit(1, request);
    }

    @Test
    public void delete_deveRetornar204_quandoSolicitado() throws Exception {
        doNothing().when(pessoaService).delete(1);

        mvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());

        verify(pessoaService, times(1)).delete(1);
    }

    @Test
    public void getAll_deveRetornar200_seExistirPessoas() throws Exception {
        when(pessoaService.getAll()).thenReturn(List.of(umaPessoaDepartamentoInterface()));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Maria Aperecida")))
                .andExpect(jsonPath("$[0].departamento", is("Comercial")))
                .andExpect(jsonPath("$[0].duracao", is(2)));

        verify(pessoaService).getAll();
    }

    @Test
    public void gastos_deveRetornar200_seRequestPassadoCorretamente() throws Exception {
        var mediaGasto = 50.0;
        when(pessoaService.mediaGasto(any(BuscaPessoaPorNomeEPeriodoRequest.class)))
                .thenReturn(mediaGasto);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/gastos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"João\", \"dataInicio\": \"2024-01-01\", \"dataFim\": \"2024-12-31\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0")); // Verifica se a resposta contém o valor esperado

        verify(pessoaService).mediaGasto(any(BuscaPessoaPorNomeEPeriodoRequest.class));
    }
}
