package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;
import java.util.List;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.dominio.AcaoCanal;
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.FunilVenda;
import br.com.esig.edu.crm.dominio.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FormularioDTO {
	
	private int id;
	
	private Campanha campanha;
	
	private AcaoCanal canal;
	
	private FunilVendaListDTO funilVenda;
	
	private List<Produto> produtos;
	
	private Instituicao instituicao;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;
	
}
