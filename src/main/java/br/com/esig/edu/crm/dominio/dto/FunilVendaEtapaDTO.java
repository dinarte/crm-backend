package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FunilVendaEtapaDTO {
	
	private int id;
	
	private String nome;
	
	private Integer ordem;
	
	private FunilVendaListDTO funilVenda;
	
	private FunilVendaEtapaTipoDTO tipo;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;
	

}
