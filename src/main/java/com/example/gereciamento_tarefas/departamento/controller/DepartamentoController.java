package com.example.gereciamento_tarefas.departamento.controller;

import com.example.gereciamento_tarefas.departamento.dto.DepartamentoResponse;
import com.example.gereciamento_tarefas.departamento.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;


    @GetMapping
    public List<DepartamentoResponse> getDepartamentos() {
        return departamentoService.getDepartamentos();
    }
}
