package com.api.newsletter.syonet.modelo;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private String dtNascimento;
}
