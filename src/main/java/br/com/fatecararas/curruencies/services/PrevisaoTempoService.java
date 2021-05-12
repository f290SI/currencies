package br.com.fatecararas.curruencies.services;

import br.com.fatecararas.curruencies.controllers.RequisicaoController;

import java.io.IOException;
import java.net.http.HttpResponse;

public class PrevisaoTempoService {

    private static final String URL = "https://api.hgbrasil.com/weather";

    public void buscarPrevisao() throws IOException, InterruptedException {
        RequisicaoController controller = new RequisicaoController();
        HttpResponse<String> stringHttpResponse = controller.enviarRequisicao(URL);
        System.out.println(stringHttpResponse.body());
    }
}
