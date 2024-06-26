package com.example.gereciamento_tarefas.pessoa.model;

import com.example.gereciamento_tarefas.departamento.model.Departamento;
import com.example.gereciamento_tarefas.pessoa.dto.PessoaRequest;
import com.example.gereciamento_tarefas.tarefa.model.Tarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO")
    private Departamento departamento;

    @OneToMany(mappedBy = "pessoa")
    private List<Tarefa> tarefas;

    public static Pessoa convertFrom(PessoaRequest request, Departamento departamento) {
        return Pessoa.builder()
                .nome(request.getNome())
                .departamento(departamento)
                .build();
    }
}
