package br.dev.hiok.aluralanguagesapi.api.v1.dto;

import br.dev.hiok.aluralanguagesapi.domain.model.Language;

public class LanguageRegisterDTO {

  private String nome;
  private String urlImagem;
  private int classificacao;

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

  public static Language toEntityModel(LanguageRegisterDTO languageRegisterDTO) {
    Language language = new Language();
    language.setName(languageRegisterDTO.getNome());
    language.setImageUrl(languageRegisterDTO.getUrlImagem());
    language.setRanking(languageRegisterDTO.getClassificacao());
    return language;
  }
  
}
