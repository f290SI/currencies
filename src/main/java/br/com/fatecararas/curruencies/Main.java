package br.com.fatecararas.curruencies;

import br.com.fatecararas.curruencies.model.currencies.Dolar;
import br.com.fatecararas.curruencies.model.currencies.Euro;
import br.com.fatecararas.curruencies.model.currencies.Moedas;
import br.com.fatecararas.curruencies.model.currencies.PesoArgentino;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        Dolar dolar = new Dolar("Dollar", 5.643D, 5.6164D, 0.78D);
        Euro euro = new Euro("Euro", 6.699D, 6.6677D, 0.77D);
        PesoArgentino pesoArgentino = new PesoArgentino("Peso Argentino", 0.061D, null, 0.18D);

        Moedas moedas = new Moedas("BRL", dolar, pesoArgentino, euro);

        moedas.getDolar().getCompra();

        moedas.getPesoArgentino().getVenda();

        moedas.getEuro().getVariacao();

        //Converter objeto em JSON
        String json = new ObjectMapper().writeValueAsString(moedas);

        System.out.println(json);


        String j = "{\"source\":\"BRL\",\"dolar\":{\"nome\":\"Dollar\",\"compra\":5.643,\"venda\":5.6164,\"variacao\":0.78},\"pesoArgentino\":{\"nome\":\"Peso Argentino\",\"compra\":0.061,\"venda\":null,\"variacao\":0.18},\"euro\":{\"nome\":\"Euro\",\"compra\":6.699,\"venda\":6.6677,\"variacao\":0.77}}";

        //Converter JSON em objetos
        Moedas moedasFromWebService = new ObjectMapper().readValue(j, Moedas.class);

        System.out.println(moedasFromWebService);
    }
}
