package com.api.newsletter.syonet.controller;

import com.api.newsletter.syonet.application.NoticiaService;
import com.api.newsletter.syonet.dtos.NoticiaDTO;
import com.api.newsletter.syonet.entities.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    @Autowired
    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarNoticia(@RequestBody NoticiaDTO noticiaDTO){
        try {
            noticiaService.isValidaNoticia(noticiaDTO);

            Noticia noticia = Noticia.builder()
                    .titulo(noticiaDTO.titulo())
                    .descricao(noticiaDTO.descricao())
                    .link(noticiaDTO.link())
                    .build();

            noticiaService.cadastrarNoticia(noticia);

            return new ResponseEntity<>("Notícia cadastrada com sucesso.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public List<Noticia> listarNoticiasNaoProcessadas(){
        return noticiaService.filtrarNaoProcessadas();
    }

}
