package br.com.alura.rh.services.reajuste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.services.validador.impl.Validador;
import lombok.NonNull;

@Service
public class ReajusteService {

    @Autowired
    private Validador validador;

    public void reajustarSalarioDoFuncionario(@NonNull final Funcionario funcionario, @NonNull final Double aumento) {
        validador.registrarObjetos(Pair.of(funcionario, aumento)).executar();
        funcionario.atualizarSalario(funcionario.getSalario() + aumento);
    }

}
