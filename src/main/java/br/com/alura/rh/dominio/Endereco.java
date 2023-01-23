package br.com.alura.rh.dominio;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {    
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}
