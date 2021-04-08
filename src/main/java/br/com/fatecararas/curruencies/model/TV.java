package br.com.fatecararas.curruencies.model;

public class TV extends EletroEletronico{

    public Float canal = 0F;

    public Float getCanal() {
        return canal;
    }

    public TV setCanal(Float canal) {
        this.canal = canal;
        return this;
    }

    public TV(String marca, String modelo, Voltagem voltagem) {
        super(marca, modelo, voltagem);
    }

    public TV(){}

    @Override
    public String toString() {
        return "TV{" +
                "marca='" + getMarca() +
                "', modelo='" + getModelo() +
                "', voltagem=" + getVoltagem() +
                ", canal=" + canal +
                '}';
    }

}
