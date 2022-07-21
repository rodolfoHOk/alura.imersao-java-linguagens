package br.dev.hiok.aluralanguagesapi.api.v1.dto;

import br.dev.hiok.aluralanguagesapi.domain.model.Language;

public class LanguageResponseDTO {

  private String id;
  private String nome;
  private String urlImagem;
  private int classificacao;
  private int usos;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUrlImagem() {
    return urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }

  public int getClassificacao() {
    return classificacao;
  }

  public void setClassificacao(int classificacao) {
    this.classificacao = classificacao;
  }

  public int getUsos() {
    return usos;
  }

  public void setUsos(int usos) {
    this.usos = usos;
  }

  public static LanguageResponseDTO toDTOModel(Language language) {
    LanguageResponseDTO languageResponse = new LanguageResponseDTO();
    languageResponse.setId(language.getId());
    languageResponse.setNome(language.getName());
    languageResponse.setUrlImagem(language.getImageUrl());
    languageResponse.setClassificacao(language.getRanking());
    languageResponse.setUsos(language.getUses());
    return languageResponse;
  }
  
}
