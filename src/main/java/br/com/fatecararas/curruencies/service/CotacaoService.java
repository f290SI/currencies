package br.com.fatecararas.curruencies.service;

import br.com.fatecararas.curruencies.controller.CotacaoController;
import br.com.fatecararas.curruencies.model.currencies.Moeda;
import br.com.fatecararas.curruencies.model.currencies.Moedas;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class CotacaoService {

    CotacaoController controller = new CotacaoController();

    public void executarCotacao() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    HttpResponse<String> response = controller.enviarRequisicaoCotacaoMoedas();
                    Moedas moedas = controller.converterParaMoedas(response);
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

    private void exibirCotacaoCompleta(Moeda moeda) {
        System.out.println(moeda.getNome());
        System.out.println(moeda.getCompra());
        System.out.println(moeda.getVenda());
        System.out.println(moeda.getVariacao());
    }
}
