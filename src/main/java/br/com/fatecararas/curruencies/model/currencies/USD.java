package br.com.fatecararas.curruencies.model.currencies;

public class USD {
    private String name;
    private Double buy;
    private Double sell;
    private Double variation;

    public USD() {
    }

    public String getName() {
        return name;
    }

    public USD setName(String name) {
        this.name = name;
        return this;
    }

    public Double getBuy() {
        return buy;
    }

    public USD setBuy(Double buy) {
        this.buy = buy;
        return this;
    }

    public Double getSell() {
        return sell;
    }

    public USD setSell(Double sell) {
        this.sell = sell;
        return this;
    }

    public Double getVariation() {
        return variation;
    }

    public USD setVariation(Double variation) {
        this.variation = variation;
        return this;
    }

    @Override
    public String toString() {
        return "USD{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                ", variation=" + variation +
                '}';
    }
}
