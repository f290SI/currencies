package br.com.fatecararas.curruencies.service;

import br.com.fatecararas.curruencies.controller.RequestController;
import br.com.fatecararas.curruencies.model.currencies.Moeda;
import br.com.fatecararas.curruencies.model.currencies.Moedas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CotacaoService {

    RequestController controller = new RequestController();
    private final String url = "https://api.hgbrasil.com/finance/quotations";

    public void executarCotacao() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    String response = controller.enviarRequisicao(url);
                    Moedas moedas = converterParaMoedas(response);
                    exibirCotacaoCompleta(moedas.getDolar());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(String.format("Hora atual: %s", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            }
        };

        //System.out.println(String.format("Hora inicial: %s", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        Timer timer = new Timer("Timer Currencies");
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }

    private Moedas converterParaMoedas(String json) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
        String nodeCurrencies = jsonNodes.get("results").get("currencies").toString();

        return mapper.readValue(nodeCurrencies, Moedas.class);
    }

    private void exibirCotacaoCompleta(Moeda moeda) {
        System.out.println(moeda.getNome());
        System.out.println(moeda.getCompra());
        System.out.println(moeda.getVenda());
        System.out.println(moeda.getVariacao());
    }
}
