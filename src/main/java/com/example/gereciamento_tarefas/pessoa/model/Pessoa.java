package com.example.gereciamento_tarefas.pessoa.model;

import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.pessoa.enums.EDepartamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DEPARTAMENTO")
    @Enumerated(EnumType.STRING)
    private EDepartamento departamento;

    public static Pessoa convertFrom(PessoaRequest request) {
        return Pessoa.builder()
                .nome(request.getNome())
                .departamento(request.getDepartamento())
                .build();
    }
}
