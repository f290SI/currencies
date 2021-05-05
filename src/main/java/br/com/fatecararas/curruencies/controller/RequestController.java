package br.com.fatecararas.curruencies.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestController {

    //https://api.hgbrasil.com/weather
    //https://api.hgbrasil.com/finance/quotations

    public String enviarRequisicao(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
    }
}
