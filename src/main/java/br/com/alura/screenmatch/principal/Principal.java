package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    private DadosTemporada dadosTemporada ;
    private int temporadas;
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private Scanner in = new Scanner(System.in);
    private final String API_KEY = "&apikey=47794d59";

    public void exibeMenu() throws JsonProcessingException {
        ConverteDados conversor = new ConverteDados();

        System.out.println("Digite a série a ser pesquisada: ");
        String serie = in.nextLine().replace(" ", "+").toLowerCase();
        String uri = ENDERECO + serie + API_KEY;
        var json = consumoApi.obterDados(uri);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        System.out.println("Deseja adicionar temporada á pesquisa?");
        var decidir = in.nextLine();


        if (decidir.equalsIgnoreCase("sim")) {
            System.out.println("Deseja pesquisar todas as temporada?");
            decidir = in.nextLine();

            if (decidir.equalsIgnoreCase("sim")) {
                List<DadosTemporada> temp = new ArrayList<>();
                for (int i = 1; i <= dados.totalTemporadas(); i++) {
                    temporadas = 1;
                    uri = ENDERECO + serie + "&season=" + i + API_KEY;
                    json = consumoApi.obterDados(uri);
                    DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                    temp.add(dadosTemporada);
                }
                //temp.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));//Printando somente os episodeos de todas as temporadas(isto é um lambda)
                List<DadosEpisodio> dadosEpisodios = temp.stream()
                        .flatMap(t -> t.episodios().stream())
                        .collect(Collectors.toList());

//                System.out.println("\n Top 5 episódios");
//                dadosEpisodios.stream()
//                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                        //.peek(e -> System.out.println("Primeiro filtro")) BOM PARA DEBUGAR
//                        .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                        .limit(5)
//                        .map(e->e.titulo().toUpperCase())
//                        .forEach(System.out::println);
                List<Episodio> episodios = temp.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(d -> new Episodio(t.numero(), d))
                        ).collect(Collectors.toList());
                episodios.forEach(System.out::println);

//                System.out.println("Digite o trecho do título");
//                var trechoTitulo = in.nextLine();
//                Optional<Episodio> episodiosBuscado = episodios.stream()
//                        .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                        .findFirst();
//                if(episodiosBuscado.isPresent()){
//                    System.out.println("Episodeo econtrado!");
//                    System.out.println(episodiosBuscado);
//                } else {
//                    System.out.println("Episodio não foi encontrado!");
//                }

                //System.out.println("Apartir de que ano você deseja ver os episodios? ");
               // var ano = in.nextInt();
                //in.nextLine();

               // LocalDate dataBusca = LocalDate.of(ano,1,1);

                //DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                //episodios.stream()
                        //.filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                        //.forEach(e -> System.out.println(
//                                "Temporada: " + e.getTemporada() +
//                                        "Episódio: " + e.getTemporada() +
//                                            " Data Lançamento"+ e.getDataLancamento().format(formatador)));


                Map<Integer,Double> avaliacaoPorTemporada = episodios.stream()
                        .filter(e-> e.getAvaliacao() > 0.0)
                        .collect(Collectors.groupingBy
                                (Episodio::getTemporada,Collectors.averagingDouble(Episodio::getAvaliacao)));
                System.out.println(avaliacaoPorTemporada);

                DoubleSummaryStatistics est = episodios.stream()
                        .filter(e-> e.getAvaliacao() > 0.0)
                        .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
                System.out.println("Média: " + est.getAverage());
                System.out.println("Melhor episódio: " + est.getMax());
                System.out.println("Pior episódio: " + est.getMin());
                System.out.println("Quantidade: " + est.getCount());

            }
            } else {
                System.out.println("Digite a temporada: ");
                temporadas = in.nextInt();
                in.nextLine();
                uri = ENDERECO + serie + "&season=" + temporadas + API_KEY;
                json = consumoApi.obterDados(uri);


                dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                System.out.println("Deseja adicionar um unico episodio á pesquisa?");
                decidir = in.nextLine();
                if (decidir.equalsIgnoreCase("sim")) {
                    System.out.println("Digite o episodio: ");
                    int episodio = in.nextInt();
                    uri = ENDERECO + serie + "&season=" + temporadas + "&episode=" + episodio + API_KEY;
                    json = consumoApi.obterDados(uri);


                    DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
                    System.out.println(dadosEpisodio);
                }
            }
            //System.out.println(dadosTemporada);
        }
        //System.out.println(dados);

}
