package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ContatoListDTO {


	private int id;
	
	private String nomeContato;
	
	private String nomeOrganizacao;
	
	private String url;
	
	private Instituicao instituicao;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;

}
