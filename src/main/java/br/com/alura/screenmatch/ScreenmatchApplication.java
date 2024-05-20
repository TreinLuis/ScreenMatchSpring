package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner in = new Scanner(System.in);
		var consumoApi = new ConsumoAPI();

		System.out.println("Digite a série a ser pesquisada: ");
		String serie = in.nextLine().replace(" ", "+").toLowerCase();

		//System.out.println("Digite a temporada: ");
		//int temporada = in.nextInt();//Caso queira a temporada add na uri "&Season=" +temporada +

		String uri = "https://www.omdbapi.com/?t="+serie+"&apikey=47794d59";
		var json = consumoApi.obterDados(uri);
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json,DadosSerie.class);
		System.out.println(dados);
	}
}
