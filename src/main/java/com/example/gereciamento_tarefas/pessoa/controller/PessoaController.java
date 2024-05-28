package com.example.gereciamento_tarefas.pessoa.controller;

import com.example.gereciamento_tarefas.pessoa.dto.BuscaPessoaPorNomeEPeriodoRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaDepartamentoInterface;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaResponse;
import com.example.gereciamento_tarefas.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public PessoaResponse save(@RequestBody PessoaRequest request) {
        return pessoaService.save(request);
    }

    @PutMapping("/{id}")
    public PessoaResponse edit(@PathVariable Integer id, @RequestBody PessoaRequest request) {
        return pessoaService.edit(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pessoaService.delete(id);
    }

    @GetMapping
    public List<PessoaDepartamentoInterface> getAll() {
        return pessoaService.getAll();
    }

    @GetMapping("/gastos")
    public Double gastos(@RequestBody BuscaPessoaPorNomeEPeriodoRequest request) {
        return pessoaService.mediaGasto(request);
    }
}
