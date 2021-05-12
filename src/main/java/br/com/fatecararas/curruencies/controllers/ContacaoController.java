package br.com.fatecararas.curruencies.controllers;

import br.com.fatecararas.curruencies.model.currencies.Moedas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ContacaoController {

    public HttpResponse<String> enviarRequisicaoCotacaoMoedas() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.hgbrasil.com/finance/quotations"))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public Moedas converterCotacaoParaMoedas(HttpResponse<String> response) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.readValue(response.body(), ObjectNode.class);
        String nodeCurrencies = jsonNodes.get("results").get("currencies").toString();

        return mapper.readValue(nodeCurrencies, Moedas.class);
    }
}
