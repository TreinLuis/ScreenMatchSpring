package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaChatCPT;
import jakarta.persistence.*;

import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Double avaliação;
    private Integer totalTemporadas;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.avaliação = OptionalDouble.of(Double.valueOf(dadosSerie.avaliação())).orElse(0);
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = atores;
        this.poster = poster;
        this.sinopse = ConsultaChatCPT.obterTraducao(dadosSerie.sinopse().trim());
    }

    public Serie() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAvaliação() {
        return avaliação;
    }

    public void setAvaliação(Double avaliação) {
        this.avaliação = avaliação;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return
                "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", avaliação=" + avaliação +
                ", totalTemporadas=" + totalTemporadas +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\''
                ;
    }
}
