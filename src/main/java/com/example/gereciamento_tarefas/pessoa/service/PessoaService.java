package com.example.gereciamento_tarefas.pessoa.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.departamento.service.DepartamentoService;
import com.example.gereciamento_tarefas.pessoa.dto.BuscaPessoaPorNomeEPeriodoRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaDepartamentoInterface;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaResponse;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    DepartamentoService departamentoService;

    public PessoaResponse save(PessoaRequest request) {
        var departamento = departamentoService.findById(request.getIdDepartamento());
        var pessoa = pessoaRepository.save(Pessoa.convertFrom(request, departamento));
        return PessoaResponse.convertFrom(pessoa);
    }

    public PessoaResponse edit(Integer id, PessoaRequest request) {
        var pessoa = findById(id);
        pessoa.setNome(request.getNome());
        pessoa.setDepartamento(departamentoService.findById(request.getIdDepartamento()));

        pessoaRepository.save(pessoa);

        return PessoaResponse.convertFrom(pessoa);
    }

    public void delete(Integer id) {
        var pessoa = findById(id);

        pessoaRepository.delete(pessoa);
    }

    public Pessoa findById(Integer id) {
        return pessoaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("A Pessoa não foi encontrado."));
    }

    public List<PessoaDepartamentoInterface> getAll() {
        return pessoaRepository.findPessoasComTotalHorasGastas();
    }

    public Double mediaGasto(BuscaPessoaPorNomeEPeriodoRequest request) {
        return pessoaRepository.findMediaHorasGastasPorTarefa(
                request.getNome(), request.getDataInicio(), request.getDataFim());
    }
}
