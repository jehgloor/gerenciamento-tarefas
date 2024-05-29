package com.example.gereciamento_tarefas.departamento.service;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.departamento.dto.DepartamentoResponseInterface;
import com.example.gereciamento_tarefas.departamento.model.Departamento;
import com.example.gereciamento_tarefas.departamento.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

    public List<DepartamentoResponseInterface> getDepartamentos() {
        return departamentoRepository.findDepartamentosComQtdPessoasETarefas();
    }

    public Departamento findById(Integer id) {
        return departamentoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("O Departamento não foi encontrado."));
    }
}
