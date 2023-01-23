package br.com.alura.rh.services.reajuste;

import java.time.LocalDate;

public class Promocao implements ReajusteTributavel {

    private Double valor;
    private LocalDate data;
    private Double tributo;


    public Promocao(final Double valor) {
        this.valor = valor;
        this.data = LocalDate.now();
    }

    @Override
    public Double valor() {
        return valor;
    }

    @Override
    public LocalDate data() {
        return data;
    }

    @Override
    public Double tributavel() {
        return tributo;
    }
    
}
