package br.com.alura.rh.services.promocao;

import org.springframework.stereotype.Service;

import br.com.alura.rh.dominio.Cargo;
import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;

@Service
public class PromocaoService {
    
    public void promover(Funcionario funcionario, boolean metaBatida){
        final Cargo cargoAtual = funcionario.getDadosPessoais().getCargo();
        if(Cargo.GERENTE == cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    
        if(metaBatida == Boolean.FALSE){
            throw new ValidacaoException("Funcionario não bateu a meta");
        }

        Cargo novoCargo = cargoAtual.getProximoCargo();
        funcionario.promover(novoCargo);
    }


}
