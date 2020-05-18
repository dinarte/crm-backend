package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MotivoDesinteresseDTO {

	private int id;
	
	private String nome;
	
	private Instituicao instituicao;
	
	private Usuario usuarioCadastro;
	
	private Date dataCadastro;

}
