package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie,Long> {//Primeiro parametro é a qual tabela se refere, já o segundo é que tipo é o id desta tabela

}
