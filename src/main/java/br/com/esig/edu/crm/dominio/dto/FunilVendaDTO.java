package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;
import java.util.List;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FunilVendaDTO {


	private int id;
	
	private String nome;
	
	private List<FunilVendaEtapaDTO> etapas;
	
	private Instituicao instituicao;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;
	
}
