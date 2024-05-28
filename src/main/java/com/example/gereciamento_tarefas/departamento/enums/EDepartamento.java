package com.example.gereciamento_tarefas.departamento.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDepartamento {

    RECURSOS_HUMANOS("Recursos humanos"),
    FINANCEIRO("Finaceiro"),
    ADMINISTRATIVO("Administrativo"),
    COMERCIAL("Comercial"),
    MARKETING("Marketing"),
    JURIDICO("Juridico"),
    OPERACIONAL("Opercional");

    private String descricao;
}
