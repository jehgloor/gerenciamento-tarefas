package com.example.gereciamento_tarefas.departamento.model;

import com.example.gereciamento_tarefas.pessoa.model.Pessoa;
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
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITULO")
    private String titulo;

    @OneToMany(mappedBy = "departamento")
    private List<Pessoa> pessoas;

    @OneToMany(mappedBy = "departamento")
    private List<Tarefa> tarefas;
}
