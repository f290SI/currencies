package br.com.fatecararas.curruencies.model.currencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Moedas {
    private String source;
    @JsonProperty("USD")
    private Dolar dolar;
    @JsonProperty("ARS")
    private PesoArgentino pesoArgentino;
    @JsonProperty("EUR")
    private Euro euro;
    @JsonProperty("JPY")
    private Yen yen;

    public Moedas(String source, Dolar dolar, PesoArgentino pesoArgentino, Euro euro) {
        this.source = source;
        this.dolar = dolar;
        this.pesoArgentino = pesoArgentino;
        this.euro = euro;
    }

    public Moedas() {
    }

    public String getSource() {
        return source;
    }

    public Moedas setSource(String source) {
        this.source = source;
        return this;
    }

    public Dolar getDolar() {
        return dolar;
    }

    public Moedas setDolar(Dolar dolar) {
        this.dolar = dolar;
        return this;
    }

    public PesoArgentino getPesoArgentino() {
        return pesoArgentino;
    }

    public Moedas setPesoArgentino(PesoArgentino pesoArgentino) {
        this.pesoArgentino = pesoArgentino;
        return this;
    }

    public Euro getEuro() {
        return euro;
    }

    public Moedas setEuro(Euro euro) {
        this.euro = euro;
        return this;
    }

    public Yen getYen() {
        return yen;
    }

    public Moedas setYen(Yen yen) {
        this.yen = yen;
        return this;
    }

    @Override
    public String toString() {
        return "Moedas{" +
                "source='" + source + '\'' +
                ", dolar=" + dolar +
                ", pesoArgentino=" + pesoArgentino +
                ", euro=" + euro +
                ", yen=" + yen +
                '}';
    }
}
