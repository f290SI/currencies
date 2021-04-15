package br.com.fatecararas.curruencies.model.composicao;

public class ControleRemoto {
    public TV tv;

    public ControleRemoto(TV tv) {
        this.tv = tv;
    }

    public ControleRemoto() {
    }

    public TV getTv() {
        return tv;
    }

    public ControleRemoto setTv(TV tv) {
        this.tv = tv;
        return this;
    }

    public void desligar() {
        if (tv == null) {
            System.out.println("TV não configurada...");
            return;
        }
        this.tv.setLigado(false);
        System.out.println("\nTV desligada!");
    }

    public void ligar() {
        if (tv == null) {
            System.out.println("\nTV não configurada...");
            return;
        }
        this.tv.setLigado(true);
        System.out.println("TV ligada!");
    }

    public void mudarCanal(Float canal) {
        this.tv.setCanal(canal);
        System.out.printf("Canal alterado: %.1f", canal);
    }

    @Override
    public String toString() {
        return "ControleRemoto{" +
                "tv=" + tv +
                '}';
    }
}
