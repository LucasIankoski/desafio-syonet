package com.api.newsletter.syonet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Noticia {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Lob
    @Column(name = "descricao", nullable = false)
    private String descricao;


    @Column(name = "link")
    private String link;

    @Column(name = "processada")
    private boolean processada = false;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return Objects.equals(id, noticia.id) && Objects.equals(titulo, noticia.titulo) && Objects.equals(descricao, noticia.descricao) && Objects.equals(link, noticia.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, link);
    }
}
