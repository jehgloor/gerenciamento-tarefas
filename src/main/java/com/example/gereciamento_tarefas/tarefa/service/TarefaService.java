package com.example.gereciamento_tarefas.tarefa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.comum.exception.ValidacaoException;
import com.example.gereciamento_tarefas.departamento.service.DepartamentoService;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.pessoa.service.PessoaService;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaAlocarPessoaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaResponse;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import com.example.gereciamento_tarefas.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    DepartamentoService departamentoService;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private PessoaService pessoaService;

    public TarefaResponse save(TarefaRequest request) {
        var departamento = departamentoService.findById(request.getIdDepartamento());
        var tarefa = tarefaRepository.save(Tarefa.convertFrom(request, departamento));
        return TarefaResponse.convertFrom(tarefa);

    }

    private Tarefa findById(Integer id) {
        return tarefaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("A Tarefa não foi encontrada."));
    }

    @Transactional
    public TarefaResponse finalizarTarefa(Integer id) {
        var tarefa = findById(id);
        validarParaFinalizar(tarefa);

        tarefa.setFinalizado(Boolean.TRUE);
        return TarefaResponse.convertFrom(tarefa);
    }

    private void validarParaFinalizar(Tarefa tarefa) {
        if (tarefa.isFinalizado()) {
            throw new ValidacaoException("A tarefa já se encontra finalizada.");
        }
    }

    @Transactional
    public TarefaResponse alocarPessoaNaTarefa(Integer id, TarefaAlocarPessoaRequest request) {
        var pessoa = pessoaService.findById(request.getPessoaId());
        var tarefa = findById(id);
        validarParaAlocar(pessoa, tarefa);

        tarefa.setPessoa(pessoa);

        return TarefaResponse.convertFrom(tarefa);
    }

    private void validarParaAlocar(Pessoa pessoa, Tarefa tarefa) {
        if (pessoa.getDepartamento() != tarefa.getDepartamento()) {
            throw new ValidacaoException("A tarefa e a pessoa devem ser do mesmo departamento.");
        }
    }

    public List<TarefaResponse> pendentes() {
        var tarefas = tarefaRepository.pendentes(3);
        return tarefas.stream().map(TarefaResponse::convertFrom).toList();
    }
}
