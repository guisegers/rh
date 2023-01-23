package br.com.alura.rh.services.validador.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.services.validador.IValidacaoReajusteSalario;

@Component
public class Validador {

    private List<Pair<Funcionario, Double>> objetos = new ArrayList<>();
    private Set<IValidacaoReajusteSalario> validadores = new HashSet<>();

    public Validador registrarValidador(final IValidacaoReajusteSalario validador) {
        validadores.add(validador);
        return this;
    }

    public Validador registrarObjetos(final Pair<Funcionario, Double> item) {
        objetos.add(item);
        return this;
    }

    /**
     * 
     */
    public void executar() {
        for (Pair<Funcionario, Double> pair : objetos) {
            for (IValidacaoReajusteSalario validador : validadores) {
                validador.validar(pair.getFirst(), pair.getSecond());
            }
        }
    }

}
