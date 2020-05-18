package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class LeadProdutoDTO {


	private int id;
	
	@JsonBackReference
	private LeadDTO lead;

	private ProdutoDTO produto;
	
	private Integer quantidade = 1;
	

	private UsuarioDTO usuarioCadastro;
	

	private Date dataCadastro;

}
