package br.com.alura.rh.services.reajuste;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;
import br.com.alura.rh.services.validador.IValidacaoReajusteSalario;
import lombok.NonNull;

@Component
public class ValidacaoPeriodicidadeEntreReajuste implements IValidacaoReajusteSalario {

    private static final Long INTERVALO_REAJUSTE_MINIMO = 6L;

    @Override
    public void validar(final Funcionario funcionario, Double valor) {
        LocalDate dataAtual = LocalDate.now();
        @NonNull
        LocalDate dataUltimoReajuste = funcionario.getDataUltimoReajuste();
        long mesesUltimoReajuste = ChronoUnit.MONTHS.between(dataUltimoReajuste, dataAtual);
        if (mesesUltimoReajuste < INTERVALO_REAJUSTE_MINIMO) {
            throw new ValidacaoException(MessageFormat.format("Intervalo entre reajustes deve ser maior que {0}",
                    INTERVALO_REAJUSTE_MINIMO.toString()));
        }
    }

}
