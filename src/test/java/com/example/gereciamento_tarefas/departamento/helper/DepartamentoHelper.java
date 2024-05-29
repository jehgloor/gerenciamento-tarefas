package com.example.gereciamento_tarefas.departamento.helper;

import com.example.gereciamento_tarefas.departamento.dto.DepartamentoResponseInterface;
import com.example.gereciamento_tarefas.departamento.model.Departamento;

public class DepartamentoHelper {

    public static DepartamentoResponseInterface umDepartamentoResponseInterface(String titulo) {
        var departamentoResponse = new DepartamentoResponseInterface() {

            @Override
            public String getTitulo() {
                return titulo;
            }

            @Override
            public int getQuantidadePessoas() {
                return 4;
            }

            @Override
            public int getQuantidadeTarefas() {
                return 3;
            }
        };
        return departamentoResponse;
    }

    public static Departamento umDepartamento(Integer id, String titulo) {
        return Departamento.builder()
                .id(id)
                .titulo(titulo)
                .build();
    }
}
