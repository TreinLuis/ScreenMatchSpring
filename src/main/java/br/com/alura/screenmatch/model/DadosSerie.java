package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//JsonAlias serve para atribuir um apelido ao campo da API que determinamos dentro dos parenteses

@JsonIgnoreProperties(ignoreUnknown = true)//Este comando ignora os dados que nao foram listados por mim na record
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("imdbRating") String avaliação,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("Plot") String sinopse) {

}
