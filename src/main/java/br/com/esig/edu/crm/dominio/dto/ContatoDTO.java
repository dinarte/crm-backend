package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ContatoDTO {


	private int id;
	
	private String nomeContato;
	
	private String nomeOrganizacao;
	
	private String url;
	
	@JsonManagedReference
	private List<EnderecoEmailDTO> emails;

	@JsonManagedReference
	private List<TelefoneDTO> telefones;

	@JsonManagedReference
	private List<EnderecoDTO> enderecos;
	
	private Instituicao instituicao;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;

}
