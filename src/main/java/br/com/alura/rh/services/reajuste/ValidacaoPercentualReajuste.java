package br.com.alura.rh.services.reajuste;

import org.springframework.stereotype.Component;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;
import br.com.alura.rh.services.validador.IValidacaoReajusteSalario;

@Component
public class ValidacaoPercentualReajuste implements IValidacaoReajusteSalario {

    private static final Double PERCENTUAL_MAXIMO = 0.4;
    
    @Override
    public void validar(final Funcionario funcionario, final Double aumento) {
        double salarioAtual = funcionario.getSalario();
        double percentualReajuste = aumento / salarioAtual;
		if (percentualReajuste > PERCENTUAL_MAXIMO) {
			throw new ValidacaoException("Reajuste nao pode ser superior a 40% do salario!");
		}
    }

}
