package br.com.alura.rh.services.reajuste;

import java.time.LocalDate;

public class Anuenio implements Reajuste {

    private Double valor;
    private LocalDate data;

    public Anuenio(final Double valor) {
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
    
}
