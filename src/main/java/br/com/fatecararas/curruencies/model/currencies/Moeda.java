package br.com.fatecararas.curruencies.model.currencies;

public abstract class Moeda {

    private String nome;
    private Double compra;
    private Double venda;
    private Double variacao;

    public Moeda(String nome, Double compra, Double venda, Double variacao) {
        this.nome = nome;
        this.compra = compra;
        this.venda = venda;
        this.variacao = variacao;
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
