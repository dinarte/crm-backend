package br.com.esig.edu.crm.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.esig.edu.crm.comum.dto.InstituicaoDTO;
import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
  
 * @author Dinarte
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class CampanhaDTO {

	private int id;
	
	private String nome;
	
	private String Descricao;
	
	private BigDecimal custo;
	
	private InstituicaoDTO instituicao;
	
	private UsuarioDTO usuarioCadastro;
	
	private Date dataCadastro;

}
