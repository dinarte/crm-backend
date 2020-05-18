package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.edu.crm.comum.dominio.Municipio;
import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class EnderecoDTO {


	private int id;
	
	private String logradouro;
	
	private String numero;
	
	private String bairro;
	
	private String tipo;
	
	private String observacao;
	
	private Municipio municipio;
	
	@JsonBackReference
	private ContatoDTO contato;
	
	private UsuarioDTO usuarioCadastro;
		
	private Date dataCadastro;
	
	
}
