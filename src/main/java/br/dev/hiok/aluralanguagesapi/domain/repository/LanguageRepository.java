package br.dev.hiok.aluralanguagesapi.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.dev.hiok.aluralanguagesapi.domain.model.Language;

public interface LanguageRepository extends MongoRepository<Language, String> {
  
}
