package br.com.alura.rh.services.reajuste;

import org.springframework.stereotype.Component;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;
import br.com.alura.rh.services.validador.IValidacaoReajusteSalario;

@Component
public class ValidacaoAumento implements IValidacaoReajusteSalario {

    @Override
    public void validar(final Funcionario funcionario, final Double aumento) {
        if(aumento == 0.0){
            throw new ValidacaoException("Aumento não pode ser 0.0");
        }
    }
    
}
