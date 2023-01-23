package br.com.alura.rh.dominio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Funcionario implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Embedded
	private DadosPessoais dadosPessoais;

	@Setter(AccessLevel.PRIVATE)
	@Column(name = "salario")
	@NonNull
	private Double salario;

	@Setter(AccessLevel.PRIVATE)
	@Column(name = "data_ultimo_reajuste")
	@NonNull
	private LocalDate dataUltimoReajuste;

	public void atualizarSalario(final Double salario) {
		this.salario = salario;
		this.dataUltimoReajuste = LocalDate.now();		
	}

    public void promover(final Cargo novoCargo) {
		this.dadosPessoais.setCargo(novoCargo);
    }

}
