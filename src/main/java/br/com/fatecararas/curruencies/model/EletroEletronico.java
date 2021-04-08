package br.com.fatecararas.curruencies.model;

public abstract class EletroEletronico {

    public String marca;
    public String modelo;
    public Voltagem voltagem;
    public boolean ligado = false;

    public EletroEletronico(String marca, String modelo, Voltagem voltagem) {
        this.marca = marca;
        this.modelo = modelo;
        this.voltagem = voltagem;
    }

    public EletroEletronico() {
    }

    public String getMarca() {
        return marca;
    }

    public EletroEletronico setMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public String getModelo() {
        return modelo;
    }

    public EletroEletronico setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public Voltagem getVoltagem() {
        return voltagem;
    }

    public EletroEletronico setVoltagem(Voltagem voltagem) {
        this.voltagem = voltagem;
        return this;
    }

    public boolean isLigado() {
        return ligado;
    }

    public EletroEletronico setLigado(boolean ligado) {
        this.ligado = ligado;
        return this;
    }
}
