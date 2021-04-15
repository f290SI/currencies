package br.com.fatecararas.curruencies.model.currencies;

public class EUR {
    private String name;
    private Double buy;
    private Double sell;
    private Double variation;

    public EUR() {
    }

    public String getName() {
        return name;
    }

    public EUR setName(String name) {
        this.name = name;
        return this;
    }

    public Double getBuy() {
        return buy;
    }

    public EUR setBuy(Double buy) {
        this.buy = buy;
        return this;
    }

    public Double getSell() {
        return sell;
    }

    public EUR setSell(Double sell) {
        this.sell = sell;
        return this;
    }

    public Double getVariation() {
        return variation;
    }

    public EUR setVariation(Double variation) {
        this.variation = variation;
        return this;
    }

    @Override
    public String toString() {
        return "EUR{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                ", variation=" + variation +
                '}';
    }
}
