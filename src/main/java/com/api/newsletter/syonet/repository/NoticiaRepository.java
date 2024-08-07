package com.api.newsletter.syonet.repository;

import com.api.newsletter.syonet.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    List<Noticia> findByNaoProcessadas();
}
