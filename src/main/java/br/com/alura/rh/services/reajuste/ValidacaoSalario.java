package br.com.alura.rh.services.reajuste;

import org.springframework.stereotype.Component;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;
import br.com.alura.rh.services.validador.IValidacaoReajusteSalario;

@Component
public class ValidacaoSalario implements IValidacaoReajusteSalario {

    @Override
    public void validar(Funcionario funcionario, Double aumento) {
        if(funcionario.getSalario() == 0.0){
            throw new ValidacaoException("Salario não pode ser 0.0"); 
         }
    }

}
