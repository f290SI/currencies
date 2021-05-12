package br.com.fatecararas.curruencies.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequisicaoController {

    public HttpResponse<String> enviarRequisicao(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
