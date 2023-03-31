package br.com.theuz.linguagensapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theuz.linguagensapi.model.Linguagem;
import br.com.theuz.linguagensapi.repository.LinguagemRepository;

@RestController
public class LinguagemController {
    
    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping(value="/linguagens")
    public List<Linguagem> findAllLanguage(){
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
    }
}
