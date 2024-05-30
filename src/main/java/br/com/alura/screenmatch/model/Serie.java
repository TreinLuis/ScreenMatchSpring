package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaChatCPT;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL)//como as propriedades secundarias sao salvas(ALL varias persistencias salvas)
    @Transient//Dps eu penso no relacionamento
    private List<Episodio> episodios = new ArrayList<>();

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.avaliação = OptionalDouble.of(Double.valueOf(dadosSerie.avaliação())).orElse(0);
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = atores;
        this.poster = poster;
        this.sinopse = ConsultaChatCPT.obterTraducao(dadosSerie.sinopse().trim());
    }

    public Serie() {}//JPA EXIGE O CONTRUTOR PADRAO



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

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
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
