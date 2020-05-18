package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.edu.crm.comum.dominio.Usuario;
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
public class TelefoneDTO {

	private int id;
	
	private String codigoArea;
	
	private String numero;
	
	private String observacao;
	
	private String tipo;
	
	@JsonBackReference
	private ContatoDTO contato;
	
	private UsuarioDTO usuarioCadastro;
	
	private Date dataCadastro;
		
}
