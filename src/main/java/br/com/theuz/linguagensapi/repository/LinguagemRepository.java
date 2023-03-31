package br.com.theuz.linguagensapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.theuz.linguagensapi.model.Linguagem;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {
    List<Linguagem> findByOrderByRanking();
    boolean existsByTitle(String title);
}
