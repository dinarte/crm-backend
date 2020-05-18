package br.com.esig.edu.crm.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.esig.edu.crm.comum.dto.InstituicaoDTO;
import br.com.esig.edu.crm.comum.dto.ServicoSerieDTO;
import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ProdutoDTO {

	private int id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private String unidade;
	
	private ServicoSerieDTO servicoSerie;

	private InstituicaoDTO instituicao;
	
	private UsuarioDTO usuarioCadastro;
	
	private Date dataCadastro;
		
}
