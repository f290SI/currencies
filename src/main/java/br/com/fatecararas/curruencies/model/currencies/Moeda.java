package br.com.fatecararas.curruencies.model.currencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Moeda {

    @JsonProperty("name")
    private String nome;
    @JsonProperty("buy")
    private Double compra;
    @JsonProperty("sell")
    private Double venda;
    @JsonProperty("variation")
    private Double variacao;
    private LocalDate localDate;

    public Moeda(String nome, Double compra, Double venda, Double variacao) {
        this.nome = nome;
        this.compra = compra;
        this.venda = venda;
        this.variacao = variacao;
        this.localDate = LocalDate.now();
    }

    public Moeda() {
    }

    public String getNome() {
        return nome;
    }

    public Moeda setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Double getCompra() {
        return compra;
    }

    public Moeda setCompra(Double compra) {
        this.compra = compra;
        return this;
    }

    public Double getVenda() {
        return venda;
    }

    public Moeda setVenda(Double venda) {
        this.venda = venda;
        return this;
    }

    public Double getVariacao() {
        return variacao;
    }

    public Moeda setVariacao(Double variacao) {
        this.variacao = variacao;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Moeda setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "nome='" + nome + '\'' +
                ", compra=" + compra +
                ", venda=" + venda +
                ", variacao=" + variacao +
                '}';
    }
}
