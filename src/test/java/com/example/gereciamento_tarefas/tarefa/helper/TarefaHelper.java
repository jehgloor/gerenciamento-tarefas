package com.example.gereciamento_tarefas.tarefa.helper;

import com.example.gereciamento_tarefas.tarefa.dto.TarefaAlocarPessoaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;

import java.time.LocalDate;

import static com.example.gereciamento_tarefas.departamento.helper.DepartamentoHelper.umDepartamento;
import static com.example.gereciamento_tarefas.pessoa.helper.PessoaHelper.umaPessoa;

public class TarefaHelper {

    private static final LocalDate prazo = LocalDate.of(2024, 05, 29);

    public static TarefaRequest umaTarefaRequest() {
        return TarefaRequest.builder()
                .idDepartamento(1)
                .prazo(prazo)
                .duracao(2)
                .titulo("Ligar para os clientes")
                .descricao("afim de aumentar as vendas, é solicitado entrar em contato com os clientes")
                .build();
    }

    public static Tarefa umaTarefa(Integer id) {
        return Tarefa.builder()
                .id(id)
                .departamento(umDepartamento(1, "Financeiro"))
                .prazo(prazo)
                .duracao(2)
                .finalizado(Boolean.FALSE)
                .titulo("LIGAR PARA OS CLIENTES")
                .descricao("Entre em contato com nossos clientes")
                .build();
    }

    public static TarefaResponse umaTarefaResponse() {
        return TarefaResponse.builder()
                .tituloDepartamento("Comercial")
                .prazo(prazo)
                .duracao(2)
                .finalizado(Boolean.FALSE)
                .titulo("LIGAR PARA OS CLIENTES")
                .descricao("Entre em contato com nossos clientes")
                .build();
    }

    public static Tarefa umaTarefaParaCalcularMediaDuracao(Integer duracao) {
        return Tarefa.builder()
                .id(1)
                .duracao(duracao)
                .prazo(LocalDate.of(2024, 05, 25))
                .pessoa(umaPessoa(1))
                .build();
    }

    public static TarefaAlocarPessoaRequest umaTarefaAlocarPessoaRequest() {
        var tarefaAlocarPessoa = new TarefaAlocarPessoaRequest();
        tarefaAlocarPessoa.setPessoaId(1);
        return tarefaAlocarPessoa;
    }
}
