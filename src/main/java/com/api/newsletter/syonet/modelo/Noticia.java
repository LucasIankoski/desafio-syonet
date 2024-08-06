package com.api.newsletter.syonet.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Noticia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

}
