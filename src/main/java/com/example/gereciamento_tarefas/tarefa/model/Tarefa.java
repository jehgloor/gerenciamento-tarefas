package com.example.gereciamento_tarefas.tarefa.model;

import com.example.gereciamento_tarefas.departamento.enums.EDepartamento;
import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
import com.example.gereciamento_tarefas.tarefa.dto.TarefaRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAREFA")
public class Tarefa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRAZO")
    private LocalDate prazo;

    @Column(name = "DEPARTAMENTO")
    @Enumerated(EnumType.STRING)
    private EDepartamento departamento;

    @Column(name = "DURACAO")
    private Integer duracao;

    @Column(name = "FINALIZADO")
    private Boolean finalizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pessoa pessoa;

    public static Tarefa convertFrom(TarefaRequest request) {
        return Tarefa.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .prazo(request.getPrazo())
                .departamento(request.getDepartamento())
                .duracao(request.getDuracao())
                .finalizado(Boolean.FALSE)
                .build();
    }
}
