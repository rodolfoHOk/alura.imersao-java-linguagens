package br.dev.hiok.aluralanguagesapi.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hiok.aluralanguagesapi.api.v1.dto.LanguageRegisterDTO;
import br.dev.hiok.aluralanguagesapi.api.v1.dto.LanguageResponseDTO;
import br.dev.hiok.aluralanguagesapi.domain.model.Language;
import br.dev.hiok.aluralanguagesapi.domain.repository.LanguageRepository;


@RestController
@RequestMapping("/api/v1")
public class LanguageController {

  // private List<Language> languages = List.of(
  //   new Language(
  //     "java",
  //     "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png",
  //     1),
  //   new Language(
  //     "javascript",
  //     "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/javascript/javascript_256x256.png",
  //     2),
  //   new Language(
  //     "php",
  //     "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/php/php_256x256.png",
  //     3)
  // );

  @Autowired
  private LanguageRepository languageRepository;
  
  @GetMapping("/linguagem-preferida")
  public String linguagemPreferida() {
    return "Oi, Java!";
  }
  
  @GetMapping("/linguagens")
  public List<LanguageResponseDTO> getLanguages() {
    // Aula 4 Desafio 2 - Devolver a listagem ordenada pelo ranking
    List<Language> languages = languageRepository.findAll(Sort.by(Direction.ASC, "ranking"));
    // Aula 4 Desafio 3 - Criar na sua API um modelo de entidade com nomes diferentes e traduzir através do uso de DTO
    List<LanguageResponseDTO> languagesResponse = languages.stream()
      .map(language -> LanguageResponseDTO.toDTOModel(language))
      .toList();
    return languagesResponse;
  }

  @PostMapping("/linguagens")
  // Aula 4 Desafio 4 - Retornar o status 201 quando um recurso for cadastrado através do POST;
  @ResponseStatus(HttpStatus.CREATED)
  public LanguageResponseDTO createLanguage(@RequestBody LanguageRegisterDTO languageRegisterDTO) {
    // Aula 4 Desafio 3 - Criar na sua API um modelo de entidade com nomes diferentes e traduzir através do uso de DTO
    Language languageToSave = LanguageRegisterDTO.toEntityModel(languageRegisterDTO);
    Language savedLanguage = languageRepository.save(languageToSave);
    return LanguageResponseDTO.toDTOModel(savedLanguage);
  }

  @GetMapping("/linguagens/{id}")
  public ResponseEntity<LanguageResponseDTO> getLanguageById(@PathVariable String id) {
    Optional<Language> languageOptional = languageRepository.findById(id);
    if (languageOptional.isPresent()) {
      // Aula 4 Desafio 3 - Criar na sua API um modelo de entidade com nomes diferentes e traduzir através do uso de DTO
      LanguageResponseDTO languageResponse = LanguageResponseDTO.toDTOModel(languageOptional.get());
      return ResponseEntity.ok(languageResponse);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Aula 4 Desafio 1 - Finalizar o CRUD para atualizar uma linguagem cadastrada;
  @PutMapping("/linguagens/{id}")
  public ResponseEntity<LanguageResponseDTO> updateLanguage(
    @PathVariable String id, @RequestBody LanguageRegisterDTO languageRegisterDTO) {
    
      Optional<Language> languageOptional = languageRepository.findById(id);
      if (languageOptional.isPresent()) {
        Language existLanguage = languageOptional.get();
        // Aula 4 Desafio 3 - Criar na sua API um modelo de entidade com nomes diferentes e traduzir através do uso de DTO
        existLanguage.setName(languageRegisterDTO.getNome());
        existLanguage.setImageUrl(languageRegisterDTO.getUrlImagem());
        existLanguage.setRanking(languageRegisterDTO.getClassificacao());
        Language savedLanguage = languageRepository.save(existLanguage);
        return ResponseEntity.ok(LanguageResponseDTO.toDTOModel(savedLanguage));
      } else {
        return ResponseEntity.notFound().build();
      }
  }

  // Aula 4 Desafio 1 - Finalizar o CRUD para excluir uma linguagem cadastrada;
  @DeleteMapping("/linguagens/{id}")
  public ResponseEntity<?> deleteLanguage(@PathVariable String id) {
    if (languageRepository.existsById(id)) {
      languageRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Aula 4 Desafio 5 - Aplicar modificações parciais no recurso através do método PATCH,
  // modificando o número de pessoas que utilizam cada linguagem de programação.
  @PatchMapping("/linguagens/{id}/incrementar")
  public ResponseEntity<LanguageResponseDTO> incrementLanguage(@PathVariable String id) {
    Optional<Language> languageOptional = languageRepository.findById(id);
    if (languageOptional.isPresent()) {
      Language language = languageOptional.get();
      language.increment();
      Language savedLanguage = languageRepository.save(language);
      return ResponseEntity.ok(LanguageResponseDTO.toDTOModel(savedLanguage));      
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  
}
