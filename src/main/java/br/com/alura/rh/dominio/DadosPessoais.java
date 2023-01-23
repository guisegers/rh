package br.com.alura.rh.dominio;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class DadosPessoais {
    
	@NonNull
	private String cpf;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "cep", column = @Column(name = "cep")),
		@AttributeOverride(name = "logradouro", column = @Column(name = "logradouro")),
		@AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
		@AttributeOverride(name = "cidade", column = @Column(name = "cidade")),
		@AttributeOverride(name = "uf", column = @Column(name = "uf"))
	})
	private Endereco endereco;

}
