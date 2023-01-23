package br.com.alura.rh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.alura.rh.dominio.Cargo;
import br.com.alura.rh.dominio.DadosPessoais;
import br.com.alura.rh.dominio.Funcionario;
import br.com.alura.rh.exceptions.ValidacaoException;
import br.com.alura.rh.services.reajuste.ReajusteService;
import br.com.alura.rh.services.reajuste.ValidacaoAumento;
import br.com.alura.rh.services.reajuste.ValidacaoPercentualReajuste;
import br.com.alura.rh.services.reajuste.ValidacaoPeriodicidadeEntreReajuste;
import br.com.alura.rh.services.reajuste.ValidacaoSalario;
import br.com.alura.rh.services.validador.impl.Validador;
import lombok.extern.log4j.Log4j2;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class ReajusteServiceTestCase {

  @Mock
  private ReajusteService reajusteService;

  @InjectMocks
  @Spy
  private Validador validador;

  @Spy
  private ValidacaoAumento validacaoAumento;
  
  @Spy
  private ValidacaoSalario validacaoSalario;
  
  @Spy
  private ValidacaoPercentualReajuste validacaoPercentualReajuste;
  
  @Spy
  private ValidacaoPeriodicidadeEntreReajuste validacaoPeriodicidadeEntreReajuste;

  @BeforeEach
  public void setUp() {
    validador.registrarValidador(validacaoAumento);
    validador.registrarValidador(validacaoSalario);
    validador.registrarValidador(validacaoPercentualReajuste);
    validador.registrarValidador(validacaoPeriodicidadeEntreReajuste);
    ReflectionTestUtils.setField(reajusteService, "validador", validador);
  }

  @Test
  public void givenReajusteSalarioFuncionario_whenSalarioFuncionarioZero_thenThrowValidacaoExcetionSalarioZero() {

    log.info("Teste Log4j");

    assertThrows(ValidacaoException.class, () -> {
      doAnswer(new Answer<Void>() {
        public Void answer(InvocationOnMock invocation) throws Throwable {
          Object[] arguments = invocation.getArguments();
          if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
            log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
            invocation.callRealMethod();
          }
          return null;
        }
      }).when(reajusteService)
          .reajustarSalarioDoFuncionario(any(Funcionario.class),
              any(Double.class));

      DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
      Funcionario funcionario = new Funcionario(dadosPessoais, 0.0, LocalDate.now().minusMonths(6));
      reajusteService.reajustarSalarioDoFuncionario(funcionario, 0.0);
    });

  }

  @Test
  public void givenReajusteSalarioFuncionario_whenAumentoSalarioZero_thenThrowValidacaoExcetionAumentoZero() {
    log.info("Teste Log4j");

    assertThrows(ValidacaoException.class, () -> {

      doAnswer(new Answer<Void>() {
        public Void answer(InvocationOnMock invocation) throws Throwable {
          Object[] arguments = invocation.getArguments();
          if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
            log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
            invocation.callRealMethod();
          }
          return null;
        }
      }).when(reajusteService)
          .reajustarSalarioDoFuncionario(any(Funcionario.class),
              any(Double.class));

      DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
      Funcionario funcionario = new Funcionario(dadosPessoais, 10000.0, LocalDate.now().minusMonths(6));
      reajusteService.reajustarSalarioDoFuncionario(funcionario, 0.0);

    });

  }

  @Test
  public void givenReajusteSalarioFuncionario_whenAumentoSalarioMaiorLimiteMaximoPercentual_thenThrowValidacaoExcetionLimiteMaximoExcedido() {
    log.info("Teste Log4j");

    assertThrows(ValidacaoException.class, () -> {

      doAnswer(new Answer<Void>() {
        public Void answer(InvocationOnMock invocation) throws Throwable {
          Object[] arguments = invocation.getArguments();
          if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
            log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
            invocation.callRealMethod();
          }
          return null;
        }
      }).when(reajusteService)
          .reajustarSalarioDoFuncionario(any(Funcionario.class),
              any(Double.class));

      DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
      Funcionario funcionario = new Funcionario(dadosPessoais, 10000.0, LocalDate.now().minusMonths(6));
      reajusteService.reajustarSalarioDoFuncionario(funcionario, 15000.0);

    });

  }

  @Test
  public void givenReajusteSalarioFuncionario_whenIntervaloReajustePequeno_thenThrowValidacaoExcetionLimiteMinimoReajuste() {
    log.info("Teste Log4j");

    assertThrows(ValidacaoException.class, () -> {

      doAnswer(new Answer<Void>() {
        public Void answer(InvocationOnMock invocation) throws Throwable {
          Object[] arguments = invocation.getArguments();
          if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
            log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
            invocation.callRealMethod();
          }
          return null;
        }
      }).when(reajusteService)
          .reajustarSalarioDoFuncionario(any(Funcionario.class),
              any(Double.class));

      DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
      Funcionario funcionario = new Funcionario(dadosPessoais, 10000.0, LocalDate.now());
      reajusteService.reajustarSalarioDoFuncionario(funcionario, 3000.0);

    });

  }

  @Test
  public void givenReajusteSalarioFuncionario_whenValorReajusteMenorQueLimitePercentualMaximo_thenSalarioReajustado() {
    log.info("Teste Log4j");

    doAnswer(new Answer<Void>() {
      public Void answer(InvocationOnMock invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
          log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
          invocation.callRealMethod();
        }
        return null;
      }
    }).when(reajusteService)
        .reajustarSalarioDoFuncionario(any(Funcionario.class),
            any(Double.class));

    DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
    Funcionario funcionario = new Funcionario(dadosPessoais, 10000.0, LocalDate.now().minusMonths(6));
    reajusteService.reajustarSalarioDoFuncionario(funcionario, 3000.0);

    assertEquals(funcionario.getSalario(), 10000.0 + 3000.0);
  }

  @Test
  public void givenReajusteSalarioFuncionario_whenIntervaloReajusteMaiorQueLimite_thenSalarioReajustado() {
    log.info("Teste Log4j");

    doAnswer(new Answer<Void>() {
      public Void answer(InvocationOnMock invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
          log.info("Argumentos ({},{})", arguments[0].toString(), arguments[1].toString());
          invocation.callRealMethod();
        }
        return null;
      }
    }).when(reajusteService)
        .reajustarSalarioDoFuncionario(any(Funcionario.class),
            any(Double.class));

    DadosPessoais dadosPessoais = new DadosPessoais("123456789-01", "123 de Oliveira 4", Cargo.ANALISTA, null);
    Funcionario funcionario = new Funcionario(dadosPessoais, 10000.0, LocalDate.now().minusMonths(6));
    reajusteService.reajustarSalarioDoFuncionario(funcionario, 3000.0);

    assertEquals(funcionario.getSalario(), 10000.0 + 3000.0);
  }

}
