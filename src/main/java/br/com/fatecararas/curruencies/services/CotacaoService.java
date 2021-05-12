package br.com.fatecararas.curruencies.services;

import br.com.fatecararas.curruencies.controllers.RequisicaoController;
import br.com.fatecararas.curruencies.model.currencies.Moeda;
import br.com.fatecararas.curruencies.model.currencies.Moedas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Timer;
import java.util.TimerTask;

public class CotacaoService {

    static final String URL = "https://api.hgbrasil.com/finance/quotations";

    public void iniciar() {
        RequisicaoController controller = new RequisicaoController();

        TimerTask cotacaoTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    HttpResponse<String> response = controller.enviarRequisicao(URL);
                    Moedas moedas = converterCotacaoParaMoedas(response);
                    exibirCotacaoCompleta(moedas.getDolar());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer("CotacaoService");
        timer.scheduleAtFixedRate(cotacaoTask, 0, 5000);
    }

    public Moedas converterCotacaoParaMoedas(HttpResponse<String> response) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.readValue(response.body(), ObjectNode.class);
        String nodeCurrencies = jsonNodes.get("results").get("currencies").toString();

        return mapper.readValue(nodeCurrencies, Moedas.class);
    }

    private void exibirCotacaoCompleta(Moeda moeda) {
        System.out.println(moeda.getNome());
        System.out.println(moeda.getCompra());
        System.out.println(moeda.getVenda());
        System.out.println(moeda.getVariacao());
        System.out.println("\n----------------------------------------------\n");
    }
}
