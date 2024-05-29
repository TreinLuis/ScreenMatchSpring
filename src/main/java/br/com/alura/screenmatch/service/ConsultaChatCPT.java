package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatCPT {
    public static String obterTraducao (String texto){
        OpenAiService service = new OpenAiService("sk-proj-4Zgd3YN2xRCLPpXFDjD5T3BlbkFJ1RBeROE810cLU2ofaW4z");

        CompletionRequest requisisao = CompletionRequest.builder().model("gpt-3.5-turbo-instruct")
                .prompt("Traduza para portugues"+ texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisisao);
        return resposta.getChoices().get(0).getText();
    }
}
