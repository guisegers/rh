package br.com.alura.rh.services.validador;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;

public interface IValidacaoReajusteSalario {
    
    public void validar(Funcionario funcionario, Double valor) throws ValidacaoException ;

}
