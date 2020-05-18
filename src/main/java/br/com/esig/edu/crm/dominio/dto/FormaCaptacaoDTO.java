package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import javax.persistence.Column;

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.edu.crm.comum.dto.InstituicaoDTO;
import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FormaCaptacaoDTO {

	private int id;
	
	private String nome;
	
	private InstituicaoDTO instituicao;
	
	private UsuarioDTO usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;

}
