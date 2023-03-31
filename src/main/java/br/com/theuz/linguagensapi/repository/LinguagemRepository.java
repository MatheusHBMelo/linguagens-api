package br.com.theuz.linguagensapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.theuz.linguagensapi.model.Linguagem;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {
    
}
