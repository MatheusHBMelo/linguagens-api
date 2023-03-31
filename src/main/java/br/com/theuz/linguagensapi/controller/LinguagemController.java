package br.com.theuz.linguagensapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(value="/linguagens/{id}")
    public Linguagem findById(@PathVariable String id){
        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> addLanguage(@RequestBody Linguagem linguagem) {
        if (repositorio.existsByTitle(linguagem.getTitle()) || repositorio.existsByRanking(linguagem.getRanking())) {
            return ResponseEntity.badRequest().build();
        }
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(linguagemSalva);
    }

    @PutMapping(value = "/linguagens/{id}")
    public Linguagem updateLanguage(@PathVariable String id, @RequestBody Linguagem novaLinguagem) {
        if (!repositorio.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            novaLinguagem.setId(id);
            Linguagem saveLanguage = repositorio.save(novaLinguagem);
            return saveLanguage;
        }
    }

    @DeleteMapping(value="linguagens/{id}")
    public void deleteLanguage(@PathVariable String id){
        if (!repositorio.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            repositorio.deleteById(id);
        }
    }
}
